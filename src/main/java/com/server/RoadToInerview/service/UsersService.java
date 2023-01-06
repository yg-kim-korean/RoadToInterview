package com.server.RoadToInerview.service;

import com.server.RoadToInerview.configuration.JWTUtil;
import com.server.RoadToInerview.configuration.VerifyResult;
import com.server.RoadToInerview.domain.users.UserOauthLoginForm;
import com.server.RoadToInerview.domain.users.UserPutForm;
import com.server.RoadToInerview.domain.users.Users;
import com.server.RoadToInerview.domain.users.UsersTokens;
import com.server.RoadToInerview.repository.UsersRepository;
import com.server.RoadToInerview.security.SHA512PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UsersService {
    private static String from_email;
    @Value("${spring.mail.username}")
    public void setFrom_email(String from_email) {
        UsersService.from_email = from_email+"@gmail.com";
    }
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    JavaMailSender javaMailSender;
    public String checking(String email, String nickname){
        Users check_email = usersRepository.findByEmail(email);
        Users check_nickname = usersRepository.findByNickname(nickname);

        if( Objects.isNull(check_nickname) && Objects.isNull(check_email) ){
            return "no";
        }
        else if( !Objects.isNull(check_email)){
            return "email";
        }
        else if( !Objects.isNull(check_nickname)){
            return "nickname";
        }
        return null;
    }
    @Transactional
    public Users signup(Users users){
        Random rand = new Random(System.nanoTime());
        StringBuilder sb = new StringBuilder();
        // 총 20문자 길이의 난수를 생성
        for(int i=0; i<20; i++) {
            // 랜덤으로 true 또는 false 생성
            if(rand.nextBoolean()) {
                sb.append(rand.nextInt(10)); //0~9까지 난수 생성
            } else {
                sb.append((char)(rand.nextInt(26)+97)); //알파벳 난수 생성
            }
        }
        users.setSalt(rand.toString());
        Users newUsers = usersRepository.save(users);
        MimeMessage message = javaMailSender.createMimeMessage();

        try {

            message.setSubject("Road To Interview 메일 인증");
            String htmlContent = "<p>" + "Road To Interview 메일 인증" +"</p>"+"<p>아래의 링크를 클릭해주세요 !</p>" +
                    "<a href='https://localhost.ga/auth?email=" +
                    users.getEmail() +
                    "&token=" +
                    rand +
                    "'>Email 인증하기</a>";
            message.setText(htmlContent,"UTF-8","html");
            //    message.setFrom("RoadToInterview@RoadToInterview.com");
            //    message.setFrom(""); from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
            message.addRecipients(Message.RecipientType.TO,users.getEmail());
            javaMailSender.send(message);
        }
        catch (MessagingException e){
            e.printStackTrace();
        }
        return newUsers;
    }
    @Transactional
    public Users login(String email, String password){
        SHA512PasswordEncoder encoder = new SHA512PasswordEncoder();
        Users users = usersRepository.findByEmail(email);
        if (encoder.matches(password,users.getPassword())){
            return users;
        }
        return null;
    }

    @Transactional
    public Boolean remove(Long id){
        usersRepository.deleteById(id);
        return true;
    }

    @Transactional
    public Users modify(UserPutForm userPutForm){

        Users newUsers = usersRepository.findByEmail(userPutForm.getEmail());
        newUsers.setNickname(userPutForm.getNickname());
        newUsers.setPassword(userPutForm.getPassword());
        newUsers.setEmail(userPutForm.getEmail());
        usersRepository.save(newUsers);

        return newUsers;
    }
    @Transactional
    public UsersTokens tokenReissue(String accessToken, String refreshToken){
        VerifyResult verifyResultAccess = JWTUtil.verifyAccess(accessToken);
        VerifyResult verifyResultRefresh = JWTUtil.verifyRefresh(refreshToken);
        UsersTokens usersTokens = new UsersTokens();

        if (verifyResultRefresh.isSuccess() && verifyResultAccess.isSuccess()){
            System.out.println(verifyResultRefresh);
            System.out.println(verifyResultAccess);
            if (verifyResultRefresh.getUsername().equals(verifyResultAccess.getUsername())){
                Users users = usersRepository.findByEmail(verifyResultAccess.getUsername());
                usersTokens.setUsers(users);
                usersTokens.setAccessToken(JWTUtil.makeAuthToken(users));
                usersTokens.setRefreshToken(JWTUtil.makeRefreshToken(users));
                usersTokens.setVerified(true);
            }
            else{
                usersTokens.setVerified(false);
            }
        }
        else{
            usersTokens.setVerified(false);
        }

        return usersTokens;
    }
    @Transactional
    public Users oauthCreateOrLogin(UserOauthLoginForm userOauthLoginForm){
        Users users = usersRepository.findByEmail(userOauthLoginForm.getEmail());
        if (Objects.isNull(users)){
            Users newUser = new Users();
            newUser.setEmail(userOauthLoginForm.getEmail());
            newUser.setManager(false);
            newUser.setSalt("asdf");
            newUser.setEmailauth(userOauthLoginForm.getEmailauth());
            newUser.setSrc(userOauthLoginForm.getSrc());
            newUser.setNickname(userOauthLoginForm.getNickname());
            users = usersRepository.save(newUser);
        }
        else if (users.getEmailauth() != userOauthLoginForm.getEmailauth()){
            return null;
        }
        return users;
    }
    @Transactional
    public void checkEmail(String email, String salt){
        Users users = usersRepository.findByEmail(email);
        if (users.getSalt() == salt){
            users.setEmailauth(1L);
            usersRepository.save(users);
        }
        else{

        }


    }


}

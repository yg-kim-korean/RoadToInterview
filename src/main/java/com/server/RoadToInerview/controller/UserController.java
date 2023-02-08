package com.server.RoadToInerview.controller;

import com.server.RoadToInerview.configuration.JWTUtil;
import com.server.RoadToInerview.domain.ResponseForm;
import com.server.RoadToInerview.domain.users.*;
import com.server.RoadToInerview.security.SHA512PasswordEncoder;
import com.server.RoadToInerview.service.CollectionsService;
import com.server.RoadToInerview.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UsersService usersService;
    private final CollectionsService collectionsService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Users users)
    {
        SHA512PasswordEncoder encoder = new SHA512PasswordEncoder();
        ResponseForm responseForm = new ResponseForm();
        Users newUsers;
        if (null== users.getEmail() || users.getEmail().isEmpty() ){
            responseForm.setMessage("회원 가입 : 이메일 체크 Server Error");
            return new ResponseEntity<>(responseForm,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else if (null== users.getNickname() || users.getNickname().isEmpty() ){
            responseForm.setMessage("회원 가입 : 닉네임 체크 Server Error");
            return new ResponseEntity<>(responseForm,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String check = usersService.checking(users.getEmail(), users.getNickname());
        users.setPassword(encoder.encode(users.getPassword()));
        if( check.equals("no")){
            newUsers = usersService.signup(users);
            return new ResponseEntity<>(newUsers,HttpStatus.CREATED);
        }
        else {
            if (check.equals("email")) {
                responseForm.setNickName("false");
                responseForm.setMessage("회원 가입 : 이미 존재하는 닉네임입니다.");
            } else if (check.equals("nickname")) {
                responseForm.setEmail("false");
                responseForm.setMessage("회원 가입 : 이미 존재하는 이메일입니다.");
            }
            return new ResponseEntity<>(responseForm,HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginForm userLoginForm, HttpServletResponse response){

        ResponseForm responseForm = new ResponseForm();
        LoginResultForm loginResultForm = new LoginResultForm();
        Users users = usersService.login(userLoginForm.getEmail(),userLoginForm.getPassword());
        if (Objects.isNull(users)){
            responseForm.setMessage("로그인 : 일치하는 유저 정보가 없습니다.");
            return new ResponseEntity<>(responseForm,HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8 ));
        String accessToken = JWTUtil.makeAuthToken(users);
        String refToken = JWTUtil.makeRefreshToken(users);
        headers.set("authentication", "bearer "+ accessToken);
        Cookie cookie = new Cookie("refreshToken",refToken);
        response.addCookie(cookie);
        loginResultForm.setUsers(users);
        loginResultForm.setAccessToken("bearer "+  accessToken);
        return new ResponseEntity<>(loginResultForm,headers, HttpStatus.OK);
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response, HttpServletRequest request){
        ResponseForm responseForm = new ResponseForm();

        try {
            request.getHeader("authentication");
        }
        catch (Exception e){
            responseForm.setMessage("로그아웃 : 로그인이 만료되었습니다.");
            return new ResponseEntity<>(responseForm, HttpStatus.UNAUTHORIZED);
        }
        responseForm.setMessage("로그아웃 되었습니다.");
        HttpHeaders headers = new HttpHeaders();
        Cookie deleteCookie = new Cookie("refreshToken",null);
        deleteCookie.setMaxAge(0);
        response.addCookie(deleteCookie);
        headers.set("authentication","null");
        return new ResponseEntity<>(responseForm, headers, HttpStatus.OK);
    }
    @GetMapping("/users") // 토큰 재발급
    public ResponseEntity<?> tokenReissue(HttpServletRequest request, HttpServletResponse response) {
        ResponseForm responseForm = new ResponseForm();
        Cookie[] cookie;
        String accessToken;
        String refreshToken = "";
        Users users;

        try {
            accessToken = request.getHeader("authentication");

        } catch (Exception e) {
            responseForm.setMessage("토큰 재발급 : 로그인이 만료되었습니다.(accessToken)");
            return new ResponseEntity<>(responseForm, HttpStatus.UNAUTHORIZED);
        }
        try {
            cookie = request.getCookies();

        } catch (Exception e) {
            responseForm.setMessage("토큰 재발급 : 로그인이 만료되었습니다.(refreshToken)");
            return new ResponseEntity<>(responseForm, HttpStatus.UNAUTHORIZED);
        }
        for (Cookie value : cookie) {

            if (value.getName().equals("refreshToken")) {
                refreshToken = value.getValue();
            }
            if (refreshToken.isEmpty()) {
                responseForm.setMessage("토큰 재발급 : 로그인이 만료되었습니다.(refreshToken)");
                return new ResponseEntity<>(responseForm, HttpStatus.UNAUTHORIZED);
            }
        }
        UsersTokens usersTokens = usersService.tokenReissue(accessToken, refreshToken);
        HttpHeaders headers = new HttpHeaders();
        System.out.println(usersTokens);
        if (usersTokens.getVerified()) {
            users = usersTokens.getUsers();

            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

            headers.set("authentication", "bearer " + usersTokens.getAccessToken());
            Cookie cookies = new Cookie("refreshToken", usersTokens.getRefreshToken());
            response.addCookie(cookies);
        } else {
            responseForm.setMessage("토큰 재발급 : 일치하는 유저 정보가 없습니다.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }
    @GetMapping("/auth") //email 인증
    public ResponseEntity<?> email_auth(@RequestParam("email") String email, @RequestParam("token") String token){
        ResponseForm responseForm = new ResponseForm();
        usersService.checkEmail(email,token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/users")//유저 수정
    public ResponseEntity<?> put_user(@RequestBody UserPutForm userPutForm, HttpServletRequest request){
        ResponseForm responseForm = new ResponseForm();
        Users newUsers;
        try {
            request.getHeader("authentication");
        }
        catch (Exception e){
            responseForm.setMessage("유저 수정 : 로그인이 만료되었습니다.");
            return new ResponseEntity<>(responseForm, HttpStatus.UNAUTHORIZED);
        }
        String check = usersService.checking(userPutForm.getEmail(), userPutForm.getNickname());
        if(Objects.equals(check, "no")){
            newUsers = usersService.modify(userPutForm);
            return new ResponseEntity<>(newUsers,HttpStatus.CREATED);
        }
        else {
            if (Objects.equals(check, "email")) {
                responseForm.setNickName("false");
                responseForm.setMessage("회원 가입 : 이미 존재하는 닉네임입니다.");
            } else if (Objects.equals(check, "nickname")) {
                responseForm.setEmail("false");
                responseForm.setMessage("회원 가입 : 이미 존재하는 이메일입니다.");
            }
            return new ResponseEntity<>(responseForm,HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/oauth")
    public ResponseEntity<?> oauth(@RequestBody UserOauthLoginForm userOauthLoginForm,HttpServletResponse response) {
        ResponseForm responseForm = new ResponseForm();
        Users users = usersService.oauthCreateOrLogin(userOauthLoginForm);
        if (Objects.isNull(users)){
            responseForm.setMessage("Oauth 로그인 : 일치하는 유저 정보가 없습니다.");
            return new ResponseEntity<>(responseForm,HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8 ));
        String accessToken = JWTUtil.makeAuthToken(users);
        String refToken = JWTUtil.makeRefreshToken(users);
        headers.set("authentication", "bearer "+ accessToken);
        Cookie cookie = new Cookie("refreshToken",refToken);
        response.addCookie(cookie);
        return new ResponseEntity<>(users,headers,HttpStatus.OK);
    }
    @PostMapping("/likes/{id}")
    public String likes(@PathVariable("id") Long id){
        return "asd";
    }
    @PostMapping("/collections/{id}")
    public ResponseEntity<?> collections(@PathVariable("id") Long interviewsId,HttpServletRequest request)
    {
        String accessToken;
        ResponseForm responseForm = new ResponseForm();
        try {
            accessToken = request.getHeader("authentication");
        } catch (Exception e) {
            responseForm.setMessage("답변 저장하기 : 로그인이 만료되었습니다.(accessToken)");
            return new ResponseEntity<>(responseForm, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(collectionsService.postCollections(interviewsId,accessToken),HttpStatus.OK);

    }
    @DeleteMapping("/collections/{id}")
    public ResponseEntity<?> del_collections(@PathVariable("id") Long interviewsId,HttpServletRequest request)
    {
        boolean success;
        String accessToken;
        ResponseForm responseForm = new ResponseForm();
        try {
            accessToken = request.getHeader("authentication");
        } catch (Exception e) {
            responseForm.setMessage("컬렉션 삭제하기 : 로그인이 만료되었습니다.(accessToken)");
            return new ResponseEntity<>(responseForm, HttpStatus.UNAUTHORIZED);
        }
        if (collectionsService.deleteCollections(accessToken,interviewsId)){
            responseForm.setMessage("컬렉션을 삭제하였습니다..");
            return new ResponseEntity<>(responseForm,HttpStatus.OK);
        }
        else{
            responseForm.setMessage("컬렉션 삭제하기 : 삭제하지 못했습니다.");
            return new ResponseEntity<>(responseForm,HttpStatus.CONFLICT);
        }
    }
}


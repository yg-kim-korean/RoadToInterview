package com.server.RoadToInerview.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.server.RoadToInerview.domain.Users;
import com.server.RoadToInerview.repository.UsersRepository;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UsersService usersService;


    @Test
    void userSignupTest(){
        Users users = new Users();
        users.setNickname("nick");
        users.setEmail("kyg0752@naver.com");
        users.setPassword("1234");
        users.setSalt("1234");
        users.setEmailauth(false);
        users.setManager(true);
        users.setSrc("1234");

        if(usersService.signup(users)){
            System.out.println("성공");
        }
        else{
            System.out.println("실패");
        }
    }
    @Test
    void userLoginTest(){
        String email = "kyg0752@naver.com";
        String password = "1234";
        Users users = usersService.login(email,password);
        System.out.println(users);
        if (!Objects.isNull(users)){
            System.out.println("asad");
        }
    }


    @DisplayName("jwt 토큰 테스트")
    @Test
    void test_1(){
        byte[] SEC_KEY = DatatypeConverter.parseBase64Binary("yognggeon"); //두가지 모두 맞춰서 시크릿 키를 만들기위해 사용
//        String okta_token = Jwts.builder().addClaims(
//                Map.of("name","kim","price","3000")
//        ).signWith(SignatureAlgorithm.HS256, SEC_KEY)
//                .setExpiration(new Date(System.currentTimeMillis()+1000)) //시간설정 지금부터 1초까지
//                .compact(); // jjwt 방식
        String oauth0_token = JWT.create().withClaim("name","yg")
                .withClaim("price",3000)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000)) //시간설정 지금부터 1초까지
                .sign(Algorithm.HMAC256(SEC_KEY)); // oauth 방식

//        Jws<Claims> tokenInfo = Jwts.parser().setSigningKey(SEC_KEY).parseClaimsJws(okta_token); //jjwt 방식
        DecodedJWT verified = JWT.require(Algorithm.HMAC256(SEC_KEY)).build().verify(oauth0_token); // oahth 방식

//        System.out.println(tokenInfo);
        System.out.println(verified.getClaims());

        try{
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(SEC_KEY)).build().verify(oauth0_token); // oahth 방식
            System.out.println(verify.getClaims());
        }catch (Exception e){ //만약 유효시간이 끝났다면
            System.out.println("유효 하지않은 토큰");
            DecodedJWT decode = JWT.decode(oauth0_token);
            System.out.println(decode.getClaims());
        }
    }
}

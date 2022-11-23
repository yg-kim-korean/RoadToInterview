package com.server.RoadToInerview.JWTTest;

import com.server.RoadToInerview.domain.UserLoginForm;
import com.server.RoadToInerview.domain.Users;
import com.server.RoadToInerview.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class JWTRequestTest extends WebIntegrationTest {
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
//            usersService
            System.out.println("성공");
        }
        else{
            System.out.println("실패");
        }
    }
    @DisplayName("1. hello 메세지 받아오기")
    @Test
    void test_1(){
        RestTemplate client = new RestTemplate();
        HttpEntity<UserLoginForm> body = new HttpEntity<>(
                UserLoginForm.builder().email("kyg0752@naver.com").password("1234").build()
        );
        ResponseEntity<Users> resp1 = client.exchange(uri("/login"), HttpMethod.POST,body,Users.class);
    }
}

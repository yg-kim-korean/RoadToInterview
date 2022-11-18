package com.server.RoadToInerview.service;

import com.server.RoadToInerview.domain.Users;
import com.server.RoadToInerview.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UsersService usersService;
    @Autowired
    UsersRepository usersRepository;

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
}

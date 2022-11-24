package com.server.RoadToInerview.controller;

import com.server.RoadToInerview.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    Users users;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/greeting")
    public String greeting(){
        return "hello";
    }

    @PostMapping("/signup")
    public String signup(){ // nickname/ email/ password/ src
        return "asd";
    }
    @PostMapping("/login")
    public String login(){
        return "asd";
    }
    @GetMapping("/logout")
    public String logout(){
        return "asd";
    }
    @GetMapping("/users") // 토큰 재발급
    public String re_token(){
        return "asd";
    }
    @GetMapping("/auth?email=a@naver.com&token=aaa")
    public String email_auth(@RequestParam("email") String email,@RequestParam("token") String token){
        return "as";
    }
    @PutMapping("/users")//유저 수정
    public String put_user(){
        return "asd";
    }
    @PostMapping("/oauth")
    public String oauth(){
        return "asd";
    }
    @PostMapping("/likes/{id}")
    public String likes(@PathVariable("id") Long id){
        return "asd";
    }
    @PostMapping("/collections/{id}")
    public String collections(@PathVariable("id") Long id){
        return "asd";
    }
    @DeleteMapping("/collections/{id}")
    public String del_collections(@PathVariable("id") Long id){
        return "asd";
    }
}


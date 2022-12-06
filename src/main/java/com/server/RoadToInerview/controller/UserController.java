package com.server.RoadToInerview.controller;

import com.server.RoadToInerview.domain.ResponseForm;
import com.server.RoadToInerview.domain.UserLoginForm;
import com.server.RoadToInerview.domain.Users;
import com.server.RoadToInerview.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/greeting")
    public String greeting(){
        return "hello";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Users users)
    {
        ResponseForm responseForm = new ResponseForm();
        String message = "";

        if (null== users.getEmail() || users.getEmail().isEmpty() ){
            message = "회원 가입 : 이메일 체크 Server Error";
            responseForm.setMessage(message);
            return new ResponseEntity<>(responseForm,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else if (null== users.getNickname() || users.getNickname().isEmpty() ){
            message = "회원 가입 : 닉네임 체크 Server Error";
            responseForm.setMessage(message);
            return new ResponseEntity<>(responseForm,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        message = usersService.signup(users);
        if (message.equals("created")){
            return new ResponseEntity<>(users,HttpStatus.CREATED);
        }
        else{

            if (message.equals("nickname")){
                responseForm.setNickName("false");
                responseForm.setMessage("회원 가입 : 이미 존재하는 닉네임입니다.");
            }
            else if (message.equals("email")){
                responseForm.setEmail("false");
                responseForm.setMessage("회원 가입 : 이미 존재하는 이메일입니다.");
            }
            return new ResponseEntity<>(responseForm,HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody UserLoginForm userLoginForm){

        Users users = usersService.login(userLoginForm.getEmail(),userLoginForm.getPassword());
//        System.out.println(users);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8 ));
        System.out.println(users);
        if (Objects.isNull(users)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/logout")
    public String logout(){
        return "asd";
    }
    @GetMapping("/users") // 토큰 재발급
    public String re_token(){
        return "asd";
    }
    @GetMapping("/auth")
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


package com.server.RoadToInerview.controller;

import com.server.RoadToInerview.configuration.JWTUtil;
import com.server.RoadToInerview.domain.LoginResultForm;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<?> login(@RequestBody UserLoginForm userLoginForm, HttpServletRequest httpServletRequest, HttpServletResponse response){
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
        loginResultForm.setAccessToken(accessToken);
        return new ResponseEntity<>(loginResultForm,headers, HttpStatus.OK);
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response, HttpServletRequest request){
        ResponseForm responseForm = new ResponseForm();
        try {
            response.getHeader("authentication");
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
    public ResponseEntity<?> re_token(HttpServletRequest request, HttpServletResponse response){
//        request.getHeaders()
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8 ));

        return new ResponseEntity<>(HttpStatus.OK);
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


package com.server.RoadToInerview.controller;

import com.server.RoadToInerview.domain.Answers.AnswersPostForm;
import com.server.RoadToInerview.domain.ResponseForm;
import com.server.RoadToInerview.service.AnswersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class AnswersController {
    private final AnswersService answersService;
    @PostMapping("/answers")
    public ResponseEntity<?> answers(@RequestBody AnswersPostForm answersPostForm, HttpServletRequest request){
        String accessToken;
        ResponseForm responseForm = new ResponseForm();
        try {
            accessToken = request.getHeader("authentication");
        } catch (Exception e) {
            responseForm.setMessage("답변 저장하기 : 로그인이 만료되었습니다.(accessToken)");
            return new ResponseEntity<>(responseForm, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(answersService.postAnswers(answersPostForm,accessToken),HttpStatus.OK);
    }
    @GetMapping("/answers") // 나의 답변 불러오기
    public ResponseEntity<?> getAnswers(HttpServletRequest request){
        String accessToken;
        ResponseForm responseForm = new ResponseForm();
        try {
            accessToken = request.getHeader("authentication");
        } catch (Exception e) {
            responseForm.setMessage("답변 저장하기 : 로그인이 만료되었습니다.(accessToken)");
            return new ResponseEntity<>(responseForm, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(answersService.getAnswers(accessToken),HttpStatus.OK);
    }
    @GetMapping("/answers/{id}")
    public String getOneAnswer(@PathVariable("id") Long id){
        return "asd";
    }
}

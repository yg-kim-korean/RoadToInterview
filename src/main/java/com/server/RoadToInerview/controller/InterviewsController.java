package com.server.RoadToInerview.controller;

import com.server.RoadToInerview.domain.ResponseForm;
import com.server.RoadToInerview.domain.interviews.Interviews;
import com.server.RoadToInerview.domain.interviews.InterviewsPostForm;
import com.server.RoadToInerview.service.InterviewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class InterviewsController {
    private final InterviewsService interviewsService;
    @PostMapping("/interviews")
    public ResponseEntity<?> interviews(@RequestBody InterviewsPostForm interviewsPostForm, HttpServletRequest request)
    {
        String accessToken;
        ResponseForm responseForm = new ResponseForm();
        try {
            accessToken = request.getHeader("authentication");
        } catch (Exception e) {
            responseForm.setMessage("토큰 재발급 : 로그인이 만료되었습니다.(accessToken)");
            return new ResponseEntity<>(responseForm, HttpStatus.UNAUTHORIZED);
        }
        Interviews interviews = interviewsService.postInterviews(interviewsPostForm,accessToken);
        if(Objects.isNull(interviews)){
            responseForm.setMessage("인터뷰 등록 : 인터뷰 등록이 실패하였습니다.");
            return new ResponseEntity<>(responseForm, HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(interviews, HttpStatus.OK);

    }
    @GetMapping("/interviews")
    public ResponseEntity<?> getInterviews(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("categorys_id") int categorys_id){
        
        return new ResponseEntity<>(interviewsService.getInterviews(page,size,categorys_id),HttpStatus.OK);
    }
    @GetMapping("/collections")
    public String getCollections(){
        return "asd";
    }
}

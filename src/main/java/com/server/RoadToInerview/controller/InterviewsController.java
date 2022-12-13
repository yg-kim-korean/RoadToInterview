package com.server.RoadToInerview.controller;

import com.server.RoadToInerview.domain.ResponseForm;
import com.server.RoadToInerview.domain.interviews.InterviewsPostForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class InterviewsController {
    @PostMapping("/interviews")
    public ResponseEntity<?> interviews(@RequestBody InterviewsPostForm interviewsPostForm, HttpServletRequest request)
    {
        String accessToken = request.getHeader("authentication");

        ResponseForm responseForm = new ResponseForm();

        return new ResponseEntity<>(responseForm, HttpStatus.OK);

    }
    @GetMapping("/interviews")
    public String getInterviews(@RequestParam("page") Long page, @RequestParam("size") Long size, @RequestParam("categorys_id") Long categorys_id){
        return "asd";
    }
    @GetMapping("/collections")
    public String getCollections(){
        return "asd";
    }
}

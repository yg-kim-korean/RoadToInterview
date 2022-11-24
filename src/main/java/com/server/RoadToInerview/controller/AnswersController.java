package com.server.RoadToInerview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswersController {
    @PostMapping("/answers")
    public String answers(){
        return "asd";
    }
    @GetMapping("/answers")
    public String getAnswers(){
        return "asd";
    }
    @GetMapping("/answers/{id}")
    public String getOneAnswer(@PathVariable("id") Long id){
        return "asd";
    }
}

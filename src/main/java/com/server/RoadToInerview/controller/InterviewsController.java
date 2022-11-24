package com.server.RoadToInerview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterviewsController {
    @PostMapping("/interviews")
    public String interviews(){
        return "asd";
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

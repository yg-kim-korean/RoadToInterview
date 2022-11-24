package com.server.RoadToInerview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @GetMapping("/categorys")
    public String getCategory(){
        return "asd";
    }
    @PostMapping("/categorys")
    public String categorys(){
        return "asd";
    }
}

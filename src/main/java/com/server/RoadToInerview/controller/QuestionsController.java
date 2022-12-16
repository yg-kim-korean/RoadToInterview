package com.server.RoadToInerview.controller;

import com.server.RoadToInerview.service.QuestionsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QuestionsController {
    private final QuestionsService questionsService;

    @GetMapping("/questions/{id}")
    public String questions(@PathVariable("id") Long id){
        return "asd";
    }
}

package com.server.RoadToInerview.controller;

import com.server.RoadToInerview.service.QuestionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QuestionsController {
    private final QuestionsService questionsService;

    @GetMapping("/questions/{id}")
    public ResponseEntity<?> questions(@PathVariable("id") int interviewId){

        return new ResponseEntity<>(questionsService.getQuestsions(interviewId),HttpStatus.OK);
    }
}

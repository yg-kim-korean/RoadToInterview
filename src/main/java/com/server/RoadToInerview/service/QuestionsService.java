package com.server.RoadToInerview.service;

import com.server.RoadToInerview.domain.Questions.Questions;
import com.server.RoadToInerview.repository.QuestsionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionsService {
    @Autowired
    QuestsionsRepository questsionsRepository;

    public List<Questions> getQuestsions(int interviewId){
        List<Questions> questionsList = questsionsRepository.findByInterviews_id(interviewId);
        return questionsList;
    }
}

package com.server.RoadToInerview.service;

import com.server.RoadToInerview.domain.interviews.Interviews;
import com.server.RoadToInerview.repository.InterviewsRepository;
import com.server.RoadToInerview.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class InterviewsService {

    @Autowired
    InterviewsRepository interviewsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Transactional
    public Interviews postInterviews(Interviews interviews){
        Interviews newInterview =  interviewsRepository.save(interviews);
        return newInterview;
    }
}

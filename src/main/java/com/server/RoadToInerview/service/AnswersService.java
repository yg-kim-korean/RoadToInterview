package com.server.RoadToInerview.service;

import com.server.RoadToInerview.configuration.JWTUtil;
import com.server.RoadToInerview.domain.Answers.Answers;
import com.server.RoadToInerview.domain.Answers.AnswersAll;
import com.server.RoadToInerview.domain.Answers.AnswersPostForm;
import com.server.RoadToInerview.domain.Questions.Questions;
import com.server.RoadToInerview.domain.users.Users;
import com.server.RoadToInerview.repository.AnswersRepository;
import com.server.RoadToInerview.repository.QuestsionsRepository;
import com.server.RoadToInerview.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswersService {
    @Autowired
    AnswersRepository answersRepository;
    @Autowired
    QuestsionsRepository questsionsRepository;
    @Autowired
    UsersRepository usersRepository;

    public Answers postAnswers(AnswersPostForm answersPostForm, String accessToken){
        String email = JWTUtil.verifyAccess(accessToken).getUsername();
        Users users = usersRepository.findByEmail(email);
        Answers postAnswers = new Answers();
        Optional<Questions> questions = questsionsRepository.findById(answersPostForm.getQuestions_id());
        if (questions.isPresent())
        {
            postAnswers.setQuestionsId(questions.get());
        }
        else{
            return null;
        }
        postAnswers.setUsersId(users);
        postAnswers.setAnswer(answersPostForm.getAnswer());


        return answersRepository.save(postAnswers);
    }

    public List<Answers> getAnswers(String accessToken){
        String email = JWTUtil.verifyAccess(accessToken).getUsername();
        Users users = usersRepository.findByEmail(email);

        return answersRepository.findByUsersId(users.getId());
    }
    public AnswersAll getAllAnsers(int interviewsId){
        AnswersAll answersAll = new AnswersAll();
        answersAll.setMobum(answersRepository.findByManagerUsers(interviewsId));
        answersAll.setAnswers(answersRepository.findByUsers(interviewsId));
        return answersAll;
    }
}

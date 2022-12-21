package com.server.RoadToInerview.service;

import com.server.RoadToInerview.configuration.JWTUtil;
import com.server.RoadToInerview.domain.Answers.Answers;
import com.server.RoadToInerview.domain.Answers.AnswersPostForm;
import com.server.RoadToInerview.domain.Questions.Questions;
import com.server.RoadToInerview.domain.users.Users;
import com.server.RoadToInerview.repository.AnswersRepository;
import com.server.RoadToInerview.repository.QuestsionsRepository;
import com.server.RoadToInerview.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
        Optional<Questions> questions = questsionsRepository.findById(answersPostForm.getQuestions_id());
        Answers postAnswers = new Answers();
        postAnswers.setQuestionsId(questions.get());
        postAnswers.setUsersId(users);
        postAnswers.setAnswer(answersPostForm.getAnswer());
        Answers newAnsers = answersRepository.save(postAnswers);

        return newAnsers;
    }

    public List<Answers> getAnswers(String accessToken){
        String email = JWTUtil.verifyAccess(accessToken).getUsername();
        Users users = usersRepository.findByEmail(email);
        List<Answers> answersList = answersRepository.findByUsersId(users.getId());
        return answersList;
    }
}

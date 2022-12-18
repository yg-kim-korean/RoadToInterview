package com.server.RoadToInerview.service;

import com.server.RoadToInerview.configuration.JWTUtil;
import com.server.RoadToInerview.domain.Categorys.Categorys;
import com.server.RoadToInerview.domain.Questions.Questions;
import com.server.RoadToInerview.domain.interviews.Interviews;
import com.server.RoadToInerview.domain.interviews.InterviewsPostForm;
import com.server.RoadToInerview.domain.users.Users;
import com.server.RoadToInerview.repository.CategorysRepository;
import com.server.RoadToInerview.repository.InterviewsRepository;
import com.server.RoadToInerview.repository.QuestsionsRepository;
import com.server.RoadToInerview.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewsService {

    @Autowired
    InterviewsRepository interviewsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CategorysRepository categorysRepository;
    @Autowired
    QuestsionsRepository questsionsRepository;
    @Transactional
    public Interviews postInterviews(InterviewsPostForm interviewsPostForm, String accessToken){
        Users users = usersRepository.findByEmail(JWTUtil.verifyAccess(accessToken).getUsername());
        Interviews interview = new Interviews();
        interview.setTitle(interviewsPostForm.getTitle());
        interview.setDescription(interviewsPostForm.getDescription());
        List<Categorys> categorysList = categorysRepository.findByCategorysByCategorys_id(interviewsPostForm.getCategorysList());
        interview.setCategorys(categorysList);
        interview.setUsersId(users);
        Interviews newInterview = interviewsRepository.save(interview);
        List<Questions> newQuestionsList = new ArrayList<>();
        for (int i = 0 ; i <interviewsPostForm.getQuestions().size();i++){
            Questions questions = interviewsPostForm.getQuestions().get(i);
            questions.setInterviewsId(newInterview);
            newQuestionsList.add(questions);
        }
        questsionsRepository.saveAll(newQuestionsList);
        return newInterview;
    }
    @Transactional
    public List<Interviews> getInterviews(int page,int size, int categorys_id){


        return interviewsRepository.findByCategorys_id(categorys_id,size,page);
    }
}

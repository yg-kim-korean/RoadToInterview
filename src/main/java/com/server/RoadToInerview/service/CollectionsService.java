package com.server.RoadToInerview.service;

import com.server.RoadToInerview.configuration.JWTUtil;
import com.server.RoadToInerview.domain.Collections.Collections;
import com.server.RoadToInerview.domain.interviews.Interviews;
import com.server.RoadToInerview.domain.users.Users;
import com.server.RoadToInerview.repository.CollectionsRepository;
import com.server.RoadToInerview.repository.InterviewsRepository;
import com.server.RoadToInerview.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionsService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CollectionsRepository collectionsRepository;
    @Autowired
    InterviewsRepository interviewsRepository;
    public Collections postCollections(Long interviewsId,String accessToken){

        Users users = usersRepository.findByEmail(JWTUtil.verifyAccess(accessToken).getUsername());
        Interviews interviews = interviewsRepository.getById(interviewsId);
        Collections collections = new Collections();
        collections.setUsersId(users);
        collections.setInterviewsId(interviews);
        return collectionsRepository.save(collections);
    }
    public Boolean deleteCollections(String accessToken,Long interviewsId){
        Users users = usersRepository.findByEmail(JWTUtil.verifyAccess(accessToken).getUsername());
        Long count = collectionsRepository.deleteByInterviewsIdAndUsersId(interviewsId, users.getId());
        if (count == 1){
            return true;
        }
        return false;
    }
}

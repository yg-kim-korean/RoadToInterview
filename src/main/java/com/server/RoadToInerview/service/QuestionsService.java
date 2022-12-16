package com.server.RoadToInerview.service;

import com.server.RoadToInerview.repository.QuestsionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionsService {
    @Autowired
    QuestsionsRepository questsionsRepository;


}

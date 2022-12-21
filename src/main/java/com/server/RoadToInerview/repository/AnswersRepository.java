package com.server.RoadToInerview.repository;

import com.server.RoadToInerview.domain.Answers.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswersRepository extends JpaRepository<Answers,Long> {

    List<Answers> findByUsersId(Long usersId);
}

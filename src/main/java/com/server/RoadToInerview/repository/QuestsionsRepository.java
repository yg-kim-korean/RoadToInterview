package com.server.RoadToInerview.repository;

import com.server.RoadToInerview.domain.Questions.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestsionsRepository extends JpaRepository<Questions,Long> {
    @Query(nativeQuery = true,value = "select * from questions where interviews_id = :interviewsId")
    List<Questions> findByInterviews_id(@Param("interviewsId") int interviewId);
}

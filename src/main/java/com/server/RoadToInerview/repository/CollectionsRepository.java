package com.server.RoadToInerview.repository;

import com.server.RoadToInerview.domain.Collections.Collections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CollectionsRepository extends JpaRepository<Collections,Long> {
    @Query(nativeQuery = true,value = "delete from collections " +
            "where interviews_id = :interviewsId " +
            "and users_id = :usersId")
    Long deleteByInterviewsIdAndUsersId(@Param("interviewsId") Long interviewsId,@Param("usersId") Long usersId);
}

package com.server.RoadToInerview.repository;

import com.server.RoadToInerview.domain.Answers.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswersRepository extends JpaRepository<Answers,Long> {

    List<Answers> findByUsersId(Long usersId);

    @Query(nativeQuery = true, value = "select a.answer, a.id, a.questions_id,q.title,q.description, q.interviews_id " +
            "from answers a join questions q " +
            "on a.questions_id = q.id " +
            "where q.interviews_id = :interviewsId " +
            "and a.users_id in (select u.id from users u where u.manager = 1) " +
            "order by a.questions_id asc, a.createdAt desc;")
    List<Answers> findByManagerUsers(@Param("interviewsId") int interviewsId);
    @Query(nativeQuery = true, value = "select a.answer, a.id, a.questions_id,q.title,q.description, q.interviews_id " +
            "from answers a join questions q " +
            "on a.questions_id = q.id " +
            "where q.interviews_id = :interviewsId " +
            "and a.users_id in (select u.id from users u where u.manager = 0) " +
            "order by a.questions_id asc, a.createdAt desc;")
    List<Answers> findByUsers(@Param("interviewsId") int interviewsId);
}

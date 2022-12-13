package com.server.RoadToInerview.repository;

import com.server.RoadToInerview.domain.interviews.Interviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewsRepository extends JpaRepository<Interviews,Long> {
}

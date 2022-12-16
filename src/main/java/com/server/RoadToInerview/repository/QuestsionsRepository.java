package com.server.RoadToInerview.repository;

import com.server.RoadToInerview.domain.Questions.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestsionsRepository extends JpaRepository<Questions,Long> {
}

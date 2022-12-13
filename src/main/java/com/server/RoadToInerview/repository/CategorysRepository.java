package com.server.RoadToInerview.repository;

import com.server.RoadToInerview.domain.Category.Categorys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorysRepository extends JpaRepository<Categorys,Long> {
}

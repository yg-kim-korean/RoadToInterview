package com.server.RoadToInerview.repository;

import com.server.RoadToInerview.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

package com.server.RoadToInerview.repository;

import com.server.RoadToInerview.domain.Categorys.Categorys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategorysRepository extends JpaRepository<Categorys,Long> {
    @Query(nativeQuery = true,value = "select * from categorys where categorys_id IN (:categorysList);" )
    List<Categorys> findByCategorysByCategorys_id(@Param("categorysList")List<Integer> categorysList);
}

package com.server.RoadToInerview.repository;

import com.server.RoadToInerview.domain.interviews.Interviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterviewsRepository extends JpaRepository<Interviews,Long> {

    @Query(nativeQuery = true,value = "select i.* \n" +
            "from interviews i, (select ci.interviews_id \n" +
            "\t\t\t\t\tfrom categorys c, cate_inter ci\n" +
            "\t\t\t\t\twhere ci.categorys_id = c.id\n" +
            "\t\t\t\t\tand c.categorys_id = :categorysId) a\n" +
            "where i.id = a.interviews_id\n" +
            "ORDER BY id DESC\n" +
            "limit :size,:page")
    List<Interviews> findByCategorys_id(@Param("categorysId") int categorysId,@Param("size") int size,@Param("page") int page);
}

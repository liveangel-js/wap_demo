package com.example.dao;

import com.example.model.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by liveangel on 2016-10-28.
 */
public interface RegionRestRepository extends PagingAndSortingRepository<Region, Long> {
    List<Region> findByNameContaining(@Param("name") String name);

//    @Query("select r from Region r where r.manager = ?1")
//    List<Region> findByManagerId(@Param("managerId") String managerId);
    @Query(value = "SELECT * FROM Region WHERE manager_id = ?1", nativeQuery = true)
    List<Region> findByManagerId(@Param("managerId") String managerId);
}

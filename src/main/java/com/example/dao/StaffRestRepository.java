package com.example.dao;

import com.example.model.Region;
import com.example.model.Staff;
import com.example.model.Supermarket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by liveangel on 2016-10-28.
 */
public interface StaffRestRepository extends PagingAndSortingRepository<Staff, Long> {
    List<Staff> findByNameContainingAndRank(@Param("name") String name, @Param("rank") String rank);
    List<Staff> findByGender(@Param("gender") String gender);

    @Query(value = "SELECT * FROM staff WHERE supermarket_id = ?1", nativeQuery = true)
    List<Staff> findBySupermarketId(@Param("supermarketId") String supermarketId);
}

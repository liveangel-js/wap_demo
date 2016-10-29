package com.example.dao;

import com.example.model.Cost;
import com.example.model.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by liveangel on 2016-10-29.
 */
public interface CostRepository extends PagingAndSortingRepository<Cost, Long> {
}
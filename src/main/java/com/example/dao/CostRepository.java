package com.example.dao;

import com.example.model.Cost;
import com.example.model.Product;
import com.example.model.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by liveangel on 2016-10-29.
 */
public interface CostRepository extends CrudRepository<Cost, Long> {
//    public List<Cost> findByProduct(Product product);
////    @Query(value = "SELECT * FROM cost WHERE product_id = ?1", nativeQuery = true)
////    public List<Cost> findByProductId(long id);
}
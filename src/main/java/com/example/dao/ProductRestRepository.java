package com.example.dao;

import com.example.model.Product;
import com.example.model.Supermarket;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by liveangel on 2016-10-28.
 */
public interface ProductRestRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findByProductType(@Param("type") String type);
    List<Product> findByProductNameContaining(@Param("name") String name);
}

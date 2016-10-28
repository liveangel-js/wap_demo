package com.example.dao;
import java.util.List;

import com.example.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
/**
 * Created by liveangel on 2016-10-19.
 */


@RepositoryRestResource(collectionResourceRel = "customer", path = "customers")
public interface CustomerRestRepository extends PagingAndSortingRepository<Customer, Long> {

    List<Customer> findByName(@Param("name") String name);
    List<Customer> findByNameContaining(@Param("name") String name);
    List<Customer> findByTelContaining(@Param("tel") String name);
    List<Customer> findByGender(@Param("gender") String gender);

}
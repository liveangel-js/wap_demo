package com.example.dao;

import com.example.model.Supermarket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by liveangel on 2016-10-28.
 */
@RepositoryRestResource(collectionResourceRel = "supermarket", path = "supermarkets")
public interface SupermarketRestRepository extends CrudRepository<Supermarket, Long> {
    List<Supermarket> findByAddressContaining(@Param("address") String address);
    List<Supermarket> findByTelContaining(@Param("tel") String tel);
}

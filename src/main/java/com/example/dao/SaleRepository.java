package com.example.dao;

import com.example.model.Sales;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by liveangel on 2016-10-29.
 */
public interface SaleRepository extends CrudRepository<Sales, Long> {
}

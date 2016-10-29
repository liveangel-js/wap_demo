package com.example.dao;

import com.example.model.Product;
import com.example.model.Promotion;
import com.example.model.Supermarket;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

/**
 * Created by liveangel on 2016-10-29.
 */
public interface PromotionRepository extends CrudRepository<Promotion, Long> {
    public Promotion findByStartDateBeforeAndEndDateAfterAndSupermarketAndProduct(Date buyDaye, Date date, Supermarket supermarket, Product product);
}

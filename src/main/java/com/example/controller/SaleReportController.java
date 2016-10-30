package com.example.controller;

//import java.sql.Timestamp;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.example.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class SaleReportController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getSalesByProductId")
    public List<Map<String, Object>> getSaleReport(@RequestParam(value = "productId", required = false) String product_id,
                                                   @RequestParam(value = "supermarketId", required = true) String supermarket_id,
                                                   @RequestParam(value = "startDate") String startDate,
                                                   @RequestParam(value = "endDate") String endDate,
                                                   @RequestParam(value = "productType", required = false) String productType
                                                   ) {
//        String sql = "SELECT AVG(price), SUM(price) FROM Product Group by product_type";
        List<Map<String, Object>> list = null;
        if(productType==null){
            String sql = "SELECT date, SUM(sale_price) as sales_volume, SUM(profit) as profit, Sum(amount) as amount FROM sales where product_id = ? and date >= ? and date<= ? and supermarket_id = ? Group by date order by date";
            list = jdbcTemplate.queryForList(sql, new Object[]{product_id,startDate,endDate, supermarket_id});

        }else{
            String sql = "SELECT date, SUM(sale_price) as sales_volume, SUM(profit) as profit, Sum(amount) as amount " +
                    "FROM sales where product_id in (select product_id from product where product_type=?) and date >= ? and date<= ? and supermarket_id = ? " +
                    "Group by date order by date";
            list = jdbcTemplate.queryForList(sql, new Object[]{productType,startDate,endDate, supermarket_id});
        }

        for(Map<String, Object> m : list){
            m.put("DATE", Utility.toTimeStamp(m.get("DATE")));
        }

        return list;
    }

}

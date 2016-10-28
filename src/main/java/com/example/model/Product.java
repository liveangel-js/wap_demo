package com.example.model;

import javax.persistence.*;

/**
 * Created by liveangel on 2016-10-28.
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long productMstId;
    private String productType;
    private String productName;
    private String priceUnit;
    @Column(name = "price", nullable = true, precision=12, scale=2)
    private double price;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}

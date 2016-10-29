package com.example.model;

import javax.persistence.*;

/**
 * Created by liveangel on 2016-10-29.
 */
@Entity
@Table(name="cost")
public class Cost {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long costId;


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    //    public Product getCostProduct() {
//        return costProduct;
//    }
//
//    public void setCostProduct(Product costProduct) {
//        this.costProduct = costProduct;
//    }
//
//    @OneToOne
//    @JoinColumn(name="productId")
//    private Product costProduct;
    private long productId;
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Column(nullable = true, precision=12, scale=2)
    private double cost;
}

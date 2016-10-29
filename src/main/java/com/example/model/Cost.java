package com.example.model;

import javax.persistence.*;

/**
 * Created by liveangel on 2016-10-29.
 */
@Entity
public class Cost {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long costId;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @OneToOne
    @JoinColumn(name="productId")
    private Product product;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Column(nullable = true, precision=12, scale=2)
    private double cost;
}

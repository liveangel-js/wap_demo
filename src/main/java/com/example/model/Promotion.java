package com.example.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liveangel on 2016-10-29.
 */
@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long promotionId;

    @OneToOne
    @JoinColumn(name ="productId", referencedColumnName = "productMstId")
    public Product product;


    @ManyToOne
    @JoinColumn(name = "supermarketId", referencedColumnName = "supermarketId")
    public Supermarket supermarket;

    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supermarket getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(Supermarket supermarket) {
        this.supermarket = supermarket;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(nullable = true, precision=3, scale=2)
    private double discount;
}

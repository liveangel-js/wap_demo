package com.example.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liveangel on 2016-10-29.
 */
@Entity
@Table(name = "sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long saleId;

    @ManyToOne
    @JoinColumn(name ="productId", referencedColumnName = "productMstId")
    private Product sourceProduct;

    public Product getSourceProduct() {
        return sourceProduct;
    }

    public void setSourceProduct(Product sourceProduct) {
        this.sourceProduct = sourceProduct;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Supermarket getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(Supermarket supermarket) {
        this.supermarket = supermarket;
    }

    @ManyToOne
    @JoinColumn(name ="customerId", referencedColumnName = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "supermarketId", referencedColumnName = "supermarketId")
    private Supermarket supermarket;

    private long amount;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "salePrice", nullable = true, precision=12, scale=2)
    private double salePrice;
    @Column(name = "costPrice", nullable = true, precision=12, scale=2)
    private double costPrice;

    public String getIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(String isPromotion) {
        this.isPromotion = isPromotion;
    }

    private String isPromotion;

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
    @Column(name = "profit", nullable = true, precision=12, scale=2)
    private double profit;


}

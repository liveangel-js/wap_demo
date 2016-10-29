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
    public Product sourceProduct;

    @ManyToOne
    @JoinColumn(name ="customerId", referencedColumnName = "customerId")
    public Customer customer;

    @ManyToOne
    @JoinColumn(name = "supermarketId", referencedColumnName = "supermarketId")
    public Supermarket supermarket;

    private long amount;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "salePrice", nullable = true, precision=12, scale=2)
    private double salePrice;
    @Column(name = "costPrice", nullable = true, precision=12, scale=2)
    private double costPrice;


}

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

    @Column(nullable = true, precision=3, scale=2)
    private double cost;
}

package com.example.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by liveangel on 2016-10-28.
 */
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long staffId;
    private String name;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    private String rank;
    private String position;


//    public Supermarket getBelongMarket() {
//        return belongMarket;
//    }
//
//    public void setBelong_market(Supermarket belong_market) {
//        this.belongMarket = belong_market;
//    }

    @ManyToOne
    @JoinColumn(name ="supermarketId", referencedColumnName = "supermarketId")
    public Supermarket belongMarket;

    @OneToMany(mappedBy = "manager")
    private List<Region> regions;

}

package com.example.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by liveangel on 2016-10-17.
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId;


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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    private String name;

    private String gender;
    private String tel;

    @OneToMany
    private List<Sales> sales;


} // class User

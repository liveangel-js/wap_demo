package com.example.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by liveangel on 2016-10-28.
 */
@Entity
@Table(name = "supermarket")
public class Supermarket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long supermarketId;
    private String address;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String tel;

//    public List<Staff> getStaffs() {
//        return staffs;
//    }
//
//    public void setStaffs(List<Staff> staffs) {
//        this.staffs = staffs;
//    }

    @OneToMany(mappedBy = "belongMarket")
    private List<Staff> staffs;
}

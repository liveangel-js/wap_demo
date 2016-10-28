package com.example.model;

import javax.persistence.*;

/**
 * Created by liveangel on 2016-10-28.
 */
@Entity
@Table(name="region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long regionId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    @ManyToOne
    @JoinColumn(name ="managerId", referencedColumnName = "staffId")
    public Staff manager;
}

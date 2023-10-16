package com.codegym.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "`table`")

public class CoffeeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "enable_flag")
    private boolean enableFlag;

    // Constructors, getters, and setters

    public CoffeeTable() {
    }

    public CoffeeTable(String name, String status, boolean enableFlag) {
        this.name = name;
        this.status = status;
        this.enableFlag = enableFlag;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(boolean enableFlag) {
        this.enableFlag = enableFlag;
    }
}

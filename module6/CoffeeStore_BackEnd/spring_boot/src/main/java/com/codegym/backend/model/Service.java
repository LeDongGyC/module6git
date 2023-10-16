package com.codegym.backend.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ServiceType serviceType;

    @Column(name = "enable_flag")
    private boolean enableFlag;

    @Column(name = "imgUrl")
    private String imgUrl;

    @Column(name = "created_date")
    private String createdDate;

    // Constructors, getters, and setters

    public Service() {
    }

    public Service(String name, double price, ServiceType serviceType, boolean enableFlag, String imgUrl, String createdDate) {
        this.name = name;
        this.price = price;
        this.serviceType = serviceType;
        this.enableFlag = enableFlag;
        this.imgUrl = imgUrl;
        this.createdDate = createdDate;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public boolean isEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(boolean enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
package com.codegym.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "created_time")
    private String createdTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private CoffeeTable table;

    @Column(name = "payment_status")
    private boolean paymentStatus;

    @Column(name = "payment_time")
    private String paymentTime;

    @OneToMany(mappedBy = "bill")
    private List<BillDetail> billDetails;

    // Constructors, getters, and setters

    public Bill() {
    }

    public Bill(String createdTime, User user, CoffeeTable table, boolean paymentStatus, String paymentTime) {
        this.createdTime = createdTime;
        this.user = user;
        this.table = table;
        this.paymentStatus = paymentStatus;
        this.paymentTime = paymentTime;
    }

    // Getters and Setters

    public List<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CoffeeTable getTable() {
        return table;
    }

    public void setTable(CoffeeTable table) {
        this.table = table;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }
}
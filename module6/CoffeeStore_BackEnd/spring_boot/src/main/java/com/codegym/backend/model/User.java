package com.codegym.backend.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "gender")
    private int gender;

    @Column(name = "salary")
    private double salary;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "enable_flag")
    private boolean enableFlag;

    @Column(name = "imgUrl")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    // Constructors, getters, and setters

    public User() {
    }

    public User(String name, String address, String phoneNumber, String birthday, int gender, double salary, Position position, Account account, boolean enableFlag, String imgUrl) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.gender = gender;
        this.salary = salary;
        this.position = position;
        this.account = account;
        this.enableFlag = enableFlag;
        this.imgUrl = imgUrl;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
}
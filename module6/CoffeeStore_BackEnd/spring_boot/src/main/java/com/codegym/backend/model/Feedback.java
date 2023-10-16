/**
 * Feedback class to define the Feedback entity
 *
 * @author TuLG
 * @version 1.0
 * @since 2023-06-13
 * fix conflict done
 */

package com.codegym.backend.model;

import javax.persistence.*;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fb_id")
    private String feedbackId;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "date")
    private String date;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private FeedbackType feedbackType;

    @Column(name = "rate")
    private Integer rate;


    // Constructors, getters, and setters

    public Feedback() {
    }


    public Feedback(String feedbackId, Bill bill, String name, String email, String date, String content, FeedbackType feedbackType, Integer rate) {

        this.feedbackId = feedbackId;
        this.bill = bill;
        this.name = name;
        this.email = email;
        this.date = date;
        this.content = content;
        this.feedbackType = feedbackType;
        this.rate = rate;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FeedbackType getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(FeedbackType feedbackType) {
        this.feedbackType = feedbackType;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
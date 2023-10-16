package com.codegym.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "feedback_img")
public class FeedbackImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    @Column(name = "imgUrl")
    private String imgUrl;

    // Constructors, getters, and setters

    public FeedbackImg() {
    }

    public FeedbackImg(Feedback feedback, String imgUrl) {
        this.feedback = feedback;
        this.imgUrl = imgUrl;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
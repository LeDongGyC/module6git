package com.codegym.backend.model;

import javax.persistence.*;

@Entity
public class FeedbackType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    public FeedbackType() {
    }

    public FeedbackType(String type) {
        this.type = type;
    }

    // Getters and Setters
    public FeedbackType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
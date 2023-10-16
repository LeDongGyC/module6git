package com.codegym.backend.dto;

public class CreateFeedback {
    private String feedbackId;
    private String name;
    private String email;
    private String date;
    private String content;
    private String feedbackType;
    private Integer rate;

    public CreateFeedback() {
    }

    public CreateFeedback(String feedbackId, String name, String email, String date, String content, String feedbackType, Integer rate) {
        this.feedbackId = feedbackId;
        this.name = name;
        this.email = email;
        this.date = date;
        this.content = content;
        this.feedbackType = feedbackType;
        this.rate = rate;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
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

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}

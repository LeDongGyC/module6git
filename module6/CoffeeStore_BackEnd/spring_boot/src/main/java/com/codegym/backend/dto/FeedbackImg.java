package com.codegym.backend.dto;

public class FeedbackImg {
    private int id;
    private FeedbackDto feedbackDto;
    private String imgUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FeedbackDto getFeedbackDto() {
        return feedbackDto;
    }

    public void setFeedbackDto(FeedbackDto feedbackDto) {
        this.feedbackDto = feedbackDto;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

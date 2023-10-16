package com.codegym.backend.service.impl;

import com.codegym.backend.dto.CreateFeedbackImg;
import com.codegym.backend.model.Feedback;
import com.codegym.backend.model.FeedbackImg;
import com.codegym.backend.service.IFeedbackImageService;
import com.codegym.backend.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackImageProcessingService {
    @Autowired
    private IFeedbackImageService feedbackImageService;
    @Autowired
    private IFeedbackService feedbackService;
    /**
     * @author DongPL
     * @version 2.0
     * @since 19/06/2023
     */
    public void createFeedbackImage(CreateFeedbackImg createFeedbackImg) {
        FeedbackImg feedbackImg = new FeedbackImg();
        Feedback feedback = feedbackService.findById(feedbackService.getLastInsert());
        feedbackImg.setFeedback(feedback);
        feedbackImg.setImgUrl(createFeedbackImg.getImgUrl());
        feedbackImageService.createFeedbackImage(feedbackImg);
    }
}

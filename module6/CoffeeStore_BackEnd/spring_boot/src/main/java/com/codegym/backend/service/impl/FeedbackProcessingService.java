package com.codegym.backend.service.impl;

import com.codegym.backend.dto.CreateFeedback;
import com.codegym.backend.model.Feedback;
import com.codegym.backend.model.FeedbackType;
import com.codegym.backend.service.IFeedbackService;
import com.codegym.backend.service.IFeedbackTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FeedbackProcessingService {
    @Autowired
    private IFeedbackService feedbackService;
    @Autowired
    private IFeedbackTypeService feedbackTypeService;

    /**
     * @author DongPL
     * @version 2.0
     * @since 19/06/2023
     */
    public void processFeedback(CreateFeedback feedbackCreate) {
        Feedback feedback = new Feedback();
        Integer index = feedbackService.getLastInsert();
        if (index == null) {
            index = 1;
        }
        if (index < 9) {
            feedback.setFeedbackId("FB000" + (index + 1));
        } else if (index < 99) {
            feedback.setFeedbackId("FB00" + (index + 1));
        } else if (index < 999) {
            feedback.setFeedbackId("FB0" + (index + 1));
        } else {
            feedback.setFeedbackId("FB" + (index + 1));
        }
        feedback.setName(feedbackCreate.getName());
        feedback.setEmail(feedbackCreate.getEmail());
        // xử lý lấy ra ngày giờ hiện tại
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH   :mm:ss dd/MM/yyyy");
        String formattedTime = currentTime.format(formatter);
        feedback.setDate(formattedTime);
        feedback.setContent(feedbackCreate.getContent());
        feedback.setRate(feedbackCreate.getRate());
        FeedbackType feedbackType = feedbackTypeService.findById(Integer.parseInt(feedbackCreate.getFeedbackType()));
        feedback.setFeedbackType(feedbackType);
        feedbackService.createFeedback(feedback);
    }

    public Integer countEmail(String email) {
        return feedbackService.countEmail(email);
    }
}

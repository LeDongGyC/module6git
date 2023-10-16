/**
 * FeedbackService class to create shared methods
 *
 * @author TuLG
 * @version 1.0
 * @since 2023-06-13
 */

package com.codegym.backend.service.impl;

import com.codegym.backend.dto.FeedbackDetailDto;
import com.codegym.backend.dto.IFeedbackDto;
import com.codegym.backend.model.Feedback;
import com.codegym.backend.repository.IFeedbackRepository;
import com.codegym.backend.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService implements IFeedbackService {
    @Autowired
    private IFeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Page<IFeedbackDto> findAll(Pageable pageable) {
        return feedbackRepository.findAllList(pageable);
    }

    @Override
    public Integer countEmail(String email) {
        return feedbackRepository.selectCountEmail(email);
    }

    @Override
    public void createFeedback(Feedback feedback) {
        feedbackRepository.createFeedback(feedback.getFeedbackId(), feedback.getName(),
                feedback.getEmail(), feedback.getDate(), feedback.getContent(), feedback.getFeedbackType().getId(), feedback.getRate());
    }

    /**
     * @author DongPL
     * @version 2.0
     * @since 19/06/2023
     */
    @Override
    public Integer getLastInsert() {
        return feedbackRepository.getLastInsert();
    }

    /**
     * @author DongPL
     * @version 2.0
     * @since 19/06/2023
     */
    @Override
    public Feedback findById(int id) {
        return feedbackRepository.findObject(id);
    }

    @Override
    public FeedbackDetailDto findFeedbackById(int id) {
        return feedbackRepository.findFeedbackById(id);
    }

    @Override
    public List<String> findImgUrlById(int id) {
        return feedbackRepository.findImgUrlById(id);
    }

    @Override
    public Page<IFeedbackDto> findListFeedbackByDate(Pageable pageable, String dateF, String dateT) {
        return feedbackRepository.findListFeedbackByDate(pageable, dateF, dateT);
    }

    @Override
    public Page<IFeedbackDto> findListFeedbackByRateAndDate(Pageable pageable, String rate, String dateF, String dateT) {
        return feedbackRepository.findListFeedbackByRateAndDate(pageable, rate, dateF, dateT);
    }
}

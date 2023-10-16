/**
 * IFeedbackService interface declares shared methods
 *
 * @author TuLG
 * @version 1.0
 * @since 2023-06-13
 */

package com.codegym.backend.service;

import com.codegym.backend.dto.FeedbackDetailDto;
import com.codegym.backend.dto.IFeedbackDto;
import com.codegym.backend.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFeedbackService {
    void createFeedback(Feedback feedback);

    /**
     * @author DongPL
     * @version 2.0
     * @since 19/06/2023
     */
    Integer getLastInsert();

    /**
     * @author DongPL
     * @version 2.0
     * @since 19/06/2023
     */
    Feedback findById(int id);
    FeedbackDetailDto findFeedbackById(int id);
    List<String> findImgUrlById(int id);
    List<Feedback> getAll();
    Page<IFeedbackDto> findListFeedbackByDate(Pageable pageable, String dateF, String dateT);
    Page<IFeedbackDto> findListFeedbackByRateAndDate(Pageable pageable, String rate, String dateF, String dateT);
    Page<IFeedbackDto> findAll(Pageable pageable);

    Integer countEmail(String email);
}

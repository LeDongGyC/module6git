package com.codegym.backend.repository;

import com.codegym.backend.model.FeedbackImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IFeedbackImageRepository extends JpaRepository<FeedbackImg, Integer> {
    /**
     * @author DongPL
     * @version 2.0
     * @since 19/06/2023
     */
    @Modifying
    @Query(value = "insert into feedback_img(feedback_id,imgUrl) values (?1,?2)", nativeQuery = true)
    void createFeedbackImg(int feedbackId, String imgUrl);
}

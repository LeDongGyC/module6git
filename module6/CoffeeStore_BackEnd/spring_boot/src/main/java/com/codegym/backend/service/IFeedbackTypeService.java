package com.codegym.backend.service;

import com.codegym.backend.model.FeedbackType;

import java.util.List;

public interface IFeedbackTypeService {
    /**
     * @author DongPL
     * @version 2.0
     * @since 19/06/2023
     */
    List<FeedbackType> selectAll();

    /**
     * @author DongPL
     * @version 2.0
     * @since 19/06/2023
     */
    FeedbackType findById(int id);
}

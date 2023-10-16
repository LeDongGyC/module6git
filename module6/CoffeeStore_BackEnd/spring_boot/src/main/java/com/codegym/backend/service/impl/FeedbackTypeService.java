package com.codegym.backend.service.impl;

import com.codegym.backend.model.FeedbackType;
import com.codegym.backend.repository.IFeedbackTypeRepository;
import com.codegym.backend.service.IFeedbackTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackTypeService implements IFeedbackTypeService {
    @Autowired
    private IFeedbackTypeRepository repository;

    @Override
    public List<FeedbackType> selectAll() {
        return repository.selectAllList();
    }
    /**
     * @author DongPL
     * @version 2.0
     * @since 19/06/2023
     */
    @Override
    public FeedbackType findById(int id) {
        return repository.findObject(id);
    }
}

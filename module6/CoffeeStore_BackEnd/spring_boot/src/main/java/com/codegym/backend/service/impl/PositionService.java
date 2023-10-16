package com.codegym.backend.service.impl;

import com.codegym.backend.model.Position;
import com.codegym.backend.repository.IPositionRepository;
import com.codegym.backend.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService implements IPositionService {

    @Autowired
    private IPositionRepository positionRepository;
    @Override
    public List<Position> getAllPosition() {
        return positionRepository.getAllPosition();
    }
}

package com.codegym.backend.service.impl;

import com.codegym.backend.model.CoffeeTable;
import com.codegym.backend.repository.ITableRepository;
import com.codegym.backend.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService implements ITableService {
    @Autowired
    ITableRepository iTableRepository;
    @Override
    public List<CoffeeTable> findAllTable() {
        return iTableRepository.findAllTable();
    }
}

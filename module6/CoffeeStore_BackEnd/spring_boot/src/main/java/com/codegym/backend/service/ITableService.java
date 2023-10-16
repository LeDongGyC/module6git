package com.codegym.backend.service;

import com.codegym.backend.model.CoffeeTable;

import java.util.List;

public interface ITableService {
    List<CoffeeTable> findAllTable();
}

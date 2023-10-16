package com.codegym.backend.service.impl;

import com.codegym.backend.dto.BillDetailListDTO;
import com.codegym.backend.model.CoffeeTable;
import com.codegym.backend.repository.ICoffeeTableRepository;
import com.codegym.backend.service.ICoffeeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeTableService implements ICoffeeTableService {
    @Autowired
    ICoffeeTableRepository tableRepository;
    @Override
    public List<CoffeeTable> getAllTable() {
        return tableRepository.getAllTable();
    }

    @Override
    public CoffeeTable findCoffeeTableById(int tableId) {
        return tableRepository.findCoffeeTableById(tableId);
    }

    @Override
    public List<BillDetailListDTO> getBillDetailByTableId(Integer tableId) {
        return tableRepository.getBillDetailByTableId(tableId);
    }

    @Override
    public void updateTableStatus(int tableId) {
        tableRepository.updateTableStatus(tableId);
    }
}

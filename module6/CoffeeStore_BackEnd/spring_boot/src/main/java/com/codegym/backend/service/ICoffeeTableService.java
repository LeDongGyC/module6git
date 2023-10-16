package com.codegym.backend.service;

import com.codegym.backend.dto.BillDetailListDTO;
import com.codegym.backend.model.CoffeeTable;

import java.util.List;

public interface ICoffeeTableService {
    List<CoffeeTable> getAllTable();
    CoffeeTable findCoffeeTableById(int tableId);
    List<BillDetailListDTO> getBillDetailByTableId(Integer tableId);
    void updateTableStatus(int tableId);
}

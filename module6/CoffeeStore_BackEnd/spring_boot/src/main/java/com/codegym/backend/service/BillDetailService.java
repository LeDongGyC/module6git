package com.codegym.backend.service;

import com.codegym.backend.dto.BillDetailDto;

import java.util.List;

public interface BillDetailService {
   List<BillDetailDto> findBillById(int id);
}

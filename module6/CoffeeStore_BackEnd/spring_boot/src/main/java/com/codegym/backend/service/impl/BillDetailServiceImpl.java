package com.codegym.backend.service.impl;

import com.codegym.backend.dto.BillDetailDto;
import com.codegym.backend.repository.BillDetailRepository;
import com.codegym.backend.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailServiceImpl implements BillDetailService
{
    @Autowired
    BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetailDto> findBillById(int id) {
        return billDetailRepository.getBillDetail(id);
    }

}

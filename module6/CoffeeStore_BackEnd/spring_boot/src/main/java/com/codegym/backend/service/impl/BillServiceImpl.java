package com.codegym.backend.service.impl;

import com.codegym.backend.dto.BillListDto;
import com.codegym.backend.repository.BillRepository;
import com.codegym.backend.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public Page<BillListDto> findAll(Pageable pageable) {
        return billRepository.findAllList(pageable);
    }

    @Override
    public Page<BillListDto> findByUser(Pageable pageable, String name) {
        return billRepository.findBillByUser(pageable,name);
    }


}

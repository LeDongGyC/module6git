package com.codegym.backend.service.impl;

import com.codegym.backend.dto.BillDetailListDTO;
import com.codegym.backend.model.BillDetail;
import com.codegym.backend.repository.IBillDetailRepository;
import com.codegym.backend.service.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BillDetailService implements IBillDetailService {
    @Autowired
    IBillDetailRepository iBillDetailRepository;

    @Override
    public void insertBillDetail(int quantity, int bill_id, int service_id) {
        iBillDetailRepository.insertBillDetail(quantity,bill_id,service_id);
    }

    @Override
    public List<BillDetailListDTO> findByBillId(int table_id) {
        return iBillDetailRepository.findByBillId(table_id);
    }
}

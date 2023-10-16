package com.codegym.backend.service.impl;

import com.codegym.backend.dto.BillDto;
import com.codegym.backend.repository.IBillRepository;
import com.codegym.backend.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService implements IBillService {
    @Autowired
    IBillRepository iBillRepository;

    @Override
    public BillDto findByIdBill(int table_id) {
        return iBillRepository.findByIdBill(table_id);
    }

    @Override
    public void insertBill(String created_time, int payment_status, String payment_time, int table_id, int user_id) {
        iBillRepository.insertBill(created_time,payment_status,payment_time,table_id,user_id);
    }
}

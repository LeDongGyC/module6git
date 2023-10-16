package com.codegym.backend.service;

import com.codegym.backend.dto.BillDetailListDTO;
import com.codegym.backend.model.BillDetail;

import java.util.List;

public interface IBillDetailService {
    void insertBillDetail(int quantity,int bill_id,int service_id);
    List<BillDetailListDTO> findByBillId(int table_id);
}

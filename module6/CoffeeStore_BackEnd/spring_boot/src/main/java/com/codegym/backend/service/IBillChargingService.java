package com.codegym.backend.service;

import com.codegym.backend.dto.BillChargingListDTO;

import java.util.List;

public interface IBillChargingService {
    List<BillChargingListDTO> getAllBillCharging(Integer tableId);
    int getUserId(String userName);
    void updateBillStatusByTableId(String paymentTime, int userId, int tableId);
}

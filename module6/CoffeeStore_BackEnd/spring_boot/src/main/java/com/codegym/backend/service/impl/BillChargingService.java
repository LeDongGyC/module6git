package com.codegym.backend.service.impl;

import com.codegym.backend.dto.BillChargingListDTO;
import com.codegym.backend.repository.IBillChargingRepository;
import com.codegym.backend.service.IBillChargingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillChargingService implements IBillChargingService {
    @Autowired
    IBillChargingRepository billChargingRepository;

    @Override
    public List<BillChargingListDTO> getAllBillCharging(Integer tableId) {
        return billChargingRepository.getAllBillCharging(tableId);
    }

    /**
     * @param userName
     * @return
     */
    @Override
    public int getUserId(String userName) {
        return billChargingRepository.getUserId(userName);
    }

    @Override
    public void updateBillStatusByTableId(String paymentTime, int userId, int tableId) {
        billChargingRepository.updateBillStatusByTableId(paymentTime, userId, tableId);
    }
}

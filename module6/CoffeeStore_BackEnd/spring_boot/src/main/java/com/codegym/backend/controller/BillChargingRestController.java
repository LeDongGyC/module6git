package com.codegym.backend.controller;

import com.codegym.backend.dto.BillChargingListDTO;
import com.codegym.backend.service.IBillChargingService;
import com.codegym.backend.service.ICoffeeTableService;
import com.codegym.backend.service.impl.AccountDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/private")
@Validated
public class BillChargingRestController {
    @Autowired
    IBillChargingService billChargingService;
    @Autowired
    ICoffeeTableService tableService;
    @Autowired
    private AccountDetailServiceImpl accountDetailService;

    /**
     * <h3>Description: Hiển thị thành tiền, trạng thái và số bàn của hóa đơn.</h3>
     *
     * @param tableId
     * @return Thành tiền, số bàn của hóa đơn
     * @author CuongHM
     */
    @GetMapping("/sales/bill-charging/{tableId}")
    public ResponseEntity<List<BillChargingListDTO>> getBillChargingByTableId(
            @Valid @PathVariable @Min(value = 0, message = "Table ID cannot lower than 0!")
            @Max(value = 100, message = "Too high table ID value!") Integer tableId) {
        List<BillChargingListDTO> billChargingList = billChargingService.getAllBillCharging(tableId);
        if (billChargingList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(billChargingList, HttpStatus.OK);
    }

    /**
     * <h3>Description: Tính tiền bàn đã chọn, và đưa bàn đó về trạng thái không có khách.</h3>
     *
     * @param tableId
     * @return Hóa đơn của bàn vừa được tính tiền
     * @author CuongHM
     */
    @GetMapping("/sales/bill-charge/{tableId}")
    public ResponseEntity<List<BillChargingListDTO>> updateBillStatus(
            @PathVariable @Min(value = 0, message = "Table ID cannot lower than 0!")
            @Max(value = 100, message = "Too high table ID value!") int tableId) {
        String userName = accountDetailService.getCurrentUserName();
        int userId = billChargingService.getUserId(userName);
        List<BillChargingListDTO> billChargingList = billChargingService.getAllBillCharging(tableId);
        if (billChargingList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        billChargingService.updateBillStatusByTableId(dtf.format(date), userId, tableId);
        tableService.updateTableStatus(tableId);
        return new ResponseEntity<>(billChargingList, HttpStatus.OK);
    }
}

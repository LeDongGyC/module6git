package com.codegym.backend.controller;

import com.codegym.backend.dto.BillDetailListDTO;
import com.codegym.backend.model.CoffeeTable;
import com.codegym.backend.service.ICoffeeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/private")
@Validated
public class CoffeeTableRestController {
    @Autowired
    ICoffeeTableService tableService;

    /**
     * <h3>Description: Hiển thị danh sách bàn không bị hư hại.</h3>
     *
     * @return List of table
     * @author CuongHM
     */
    @GetMapping("/sales")
    public ResponseEntity<Iterable<CoffeeTable>> getAllTable() {
        List<CoffeeTable> tableList = tableService.getAllTable();
        if (tableList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tableList, HttpStatus.OK);
    }

    /**
     * <h3>Description: Hiển thị hóa đơn các món đã chọn của bàn đó.</h3>
     *
     * @param tableId
     * @return Danh sách chi tiết món ăn, số lượng và tổng giá từng món.
     * @author CuongHM
     */
    @GetMapping("/sales/bill/{tableId}")
    public ResponseEntity<List<BillDetailListDTO>> getBillDetailByTableId(
            @PathVariable @Min(value = 0, message = "Table ID cannot lower than 0!")
            @Max(value = 100, message = "Too high table ID value!") Integer tableId) {
        List<BillDetailListDTO> billDetailListDTOList = tableService.getBillDetailByTableId(tableId);
        if (billDetailListDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(billDetailListDTOList, HttpStatus.OK);
    }
}

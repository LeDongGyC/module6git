/**
 * BillService interface declares shared methods
 * @author BaoDV
 * @version 1.0
 * @since 2023-06-14
 */
package com.codegym.backend.service;

import com.codegym.backend.dto.BillDetailDto;
import com.codegym.backend.dto.BillListDto;
import com.codegym.backend.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BillService {
    Page<BillListDto> findAll(Pageable pageable);
    Page<BillListDto> findByUser(Pageable pageable,String name);

}

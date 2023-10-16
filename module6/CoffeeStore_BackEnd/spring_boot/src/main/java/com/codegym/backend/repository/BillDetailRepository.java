package com.codegym.backend.repository;

import com.codegym.backend.dto.BillDetailDto;
import com.codegym.backend.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail,Integer> {
    @Query(value = "select s.name, s.price,bd.quantity, bd.quantity*s.price as total from service s " +
            "join bill_detail bd on bd.service_id = s.id " +
            "join bill b on b.id = bd.bill_id " +
            "where b.id =?", nativeQuery = true)
    List<BillDetailDto> getBillDetail(int id);
}

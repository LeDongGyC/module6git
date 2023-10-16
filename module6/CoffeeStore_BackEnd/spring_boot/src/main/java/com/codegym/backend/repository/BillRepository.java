package com.codegym.backend.repository;

import com.codegym.backend.dto.BillDetailDto;
import com.codegym.backend.dto.BillListDto;
import com.codegym.backend.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    String SelectAllBill_Sql = "select b.id,b.created_time as createdTime, t.id as tableNumber, u.name, sum(bd.quantity*s.price) as totalPrice from  bill b\n" +
            "            join `table` t on b.table_id = t.id\n" +
            "            join user u on b.user_id =u.id\n" +
            "            join bill_detail bd on bd.bill_id = b.id\n" +
            "            join service s on bd.service_id = s.id\n" +
            "            group by b.id\n" +
            "            order by b.id";
    @Query(value = SelectAllBill_Sql, countQuery = SelectAllBill_Sql, nativeQuery = true)
    Page<BillListDto> findAllList(Pageable pageable);

    String SelectBillListByUser_Sql = "select b.id,b.created_time as createdTime, t.id as tableNumber, u.name, sum(bd.quantity*s.price) as totalPrice from  bill b\n" +
            "            join `table` t on b.table_id = t.id\n" +
            "            join user u on b.user_id =u.id\n" +
            "            join bill_detail bd on bd.bill_id = b.id\n" +
            "            join service s on bd.service_id = s.id\n" +
            "            where u.name like ?\n " +
            "            group by b.id\n" +
            "            order by b.id";
    @Query(value = SelectBillListByUser_Sql, countQuery = SelectBillListByUser_Sql, nativeQuery = true)
    Page<BillListDto> findBillByUser(Pageable pageable,String name);
}

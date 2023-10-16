package com.codegym.backend.repository;

import com.codegym.backend.dto.BillDetailListDTO;
import com.codegym.backend.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface IBillDetailRepository extends JpaRepository<BillDetail,Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into bill_detail(quantity,bill_id,service_id)" +
            "values(?1,?2,?3)",nativeQuery = true)
    void insertBillDetail(int quantity,int bill_id,int service_id);

    @Transactional
    @Modifying
    @Query(value = "select t.id as tableId, s.img_url as imgUrl, s.name as serviceName," +
            " sum(bd.quantity) quantity, s.price, t.name as tableName, sum(quantity*s.price) as sum,\n" +
            "b.payment_status as paymentStatus from bill b\n" +
            "join `table` t on b.table_id = t.id\n" +
            "join bill_detail bd on b.id = bd.bill_id\n" +
            "join service s on bd.service_id = s.id\n" +
            "where payment_status = 0 and table_id = ?\n" +
            "group by s.id\n" +
            "order by table_id asc",nativeQuery = true)
    List<BillDetailListDTO> findByBillId(int table_id);
}

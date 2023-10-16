package com.codegym.backend.repository;

import com.codegym.backend.dto.BillDto;
import com.codegym.backend.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IBillRepository extends JpaRepository<Bill,Integer> {

    @Query(value = "select b.id,b.created_time as createdTime,b.payment_status as paymentStatus," +
            "b.payment_time as paymentTime, b.table_id as tableId, b.user_id as userId from bill b where table_id = ?1 and payment_status = 0 order by id desc limit 1",nativeQuery = true)
    BillDto findByIdBill(int table_id);

    @Modifying
    @Query(value = "insert into bill (created_time,payment_status,payment_time,table_id,user_id) " +
            "values(?1,?2,?3,?4,?5)",nativeQuery = true)
    void insertBill(String created_time,int payment_status,String payment_time,int table_id,int user_id);
}

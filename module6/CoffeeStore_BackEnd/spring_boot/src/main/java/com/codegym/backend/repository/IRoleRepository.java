package com.codegym.backend.repository;

import com.codegym.backend.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<AppRole, Integer> {
    @Query(value = "SELECT * FROM role", nativeQuery = true)
    List<AppRole> findAllRole();

    //Trong trường hợp dùng Native Query thì chúng ta phải kết hợp @Modifiying và câu
    // lệnh Insert chung với nhau vì Spring Data JPA không hỗ trợ chức năng Insert.
    @Modifying
    @Query(value = "insert into account_role(account_id,role_id) values (?1,?2)", nativeQuery = true)
    void setDefaultRole(int accountId, Integer roleId);

}

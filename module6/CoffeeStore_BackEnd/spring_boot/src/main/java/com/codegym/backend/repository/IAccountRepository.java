package com.codegym.backend.repository;

import com.codegym.backend.model.Account;
import com.codegym.backend.dto.AccountListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


import java.util.List;



@Repository
@Transactional
public interface IAccountRepository extends JpaRepository<Account, Integer> {


    /**
     * ThangLV
     * change password
     */
    @Modifying
    @Query(value = "update account set password =?1 where user_name=?2 ", nativeQuery = true)
    void changePassword(String password, String userName);

    /**
     * ThangLV
     * get current password
     */
    @Query(value = "select password from account where user_name = ?", nativeQuery = true)
    String getCurrentPassword(String userName);


    @Query(value = "SELECT * FROM account where user_name = ?1", nativeQuery = true)
    Optional<Account> findAccountByUserName(String username);

    @Query(value = "SELECT id FROM account where user_name = ?1", nativeQuery = true)
    Integer findIdByUserName(String username);

    @Query(value = "SELECT user_name FROM account where user_name = ?1", nativeQuery = true)
    String existsByUserName(String username);

    @Query(value = "SELECT email FROM account where email = ?1", nativeQuery = true)
    String existsByEmail(String email);

    @Query(value = "SELECT change_password_date FROM account where user_name = ?1", nativeQuery = true)
    String findChangPassworDateByUserName(String username);

    @Modifying
    @Query(value = "update account set verification_code= ?1 where user_name = ?2", nativeQuery = true)
    void addVerificationCode(String code, String username);

    @Query(value = "select * from account where verification_code = ?1", nativeQuery = true)
    Account findAccountByVerificationCode(String code);

    @Modifying
    @Query(value = "update account set password = ?1,verification_code= null where verification_code= ?2", nativeQuery = true)
    void saveNewPassword(String encryptPassword, String code);


//    Account findAccountByUserName(String username);

    @Query(value = "select id from  a0622i1_coffee.account where user_name = ?1", nativeQuery = true)
    Integer findIdUserByUserName(String userName);

    @Modifying
    @Query(value="update a0622i1_coffee.account set account.email = ?1 where account.user_name = ?2", nativeQuery = true)
    void updateEmailAccount(String email, String username);

    @Query(value="select a.id, a.email, a.user_name from a0622i1_coffee.account a ", nativeQuery =true)
    List<AccountListDTO> findAllAccount();

}

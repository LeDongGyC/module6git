package com.codegym.backend.service;

import com.codegym.backend.dto.AccountListDTO;
import com.codegym.backend.model.Account;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IAccountService {

    /**
     * ThangLV
     * authenticate Account
     */
    Boolean checkPassword(String password, String username);

    /**
     * ThangLV
     * change password
     */
    void changePassword(String password, String username) ;

    Optional<Account> findAccountByUserName(String username);

    Integer findIdByUserName(String username);

    String existsByUserName(String username);

    String existsByEmail(String email);

    void addVerificationCode(String username) throws MessagingException, UnsupportedEncodingException;

    void sendVerificationEmailForResetPassWord(String name, String randomCode, String email) throws MessagingException, UnsupportedEncodingException;

    Account findAccountByVerificationCode(String code);

    void saveNewPassword(String encryptPassword, String code);

    String findChangPassworDateByUserName(String username);

    Boolean checkChangePasswordDateByUserName(String username) throws ParseException;


}

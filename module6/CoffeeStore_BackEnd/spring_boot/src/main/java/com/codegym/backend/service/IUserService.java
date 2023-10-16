package com.codegym.backend.service;

import com.codegym.backend.dto.*;
import com.codegym.backend.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.util.List;


public interface IUserService {

    User findByAccountId(int accountId, Boolean enableFlag);

    String findNameByAccountId(int accountId, Boolean enableFlag);

    Page<IUserDto> findUserByNameOrDate(Pageable pageable, String date, String name);

    Page<IUserDto> findUserByName(Pageable pageable, String name);

    Page<IUserDto> findAll(Pageable pageable);

    void deleteById(int id);

    /**
     * ThangLV
     * get information of User by Username
     */
    IUserInforDTO findUserByUsername(String username);

    List<IUserDto> findAllUser();

    UserFindIdDTO getById(int id);

    void editUser(UserEditDTO userEditDTO, int id) throws MessagingException;

    void createNewUser(UserDTO userDTO) throws MessagingException;

    Integer findByPhone(String phoneNumber);

    Integer findByUserName(String userName);

    Integer findByEmail(String email);

}

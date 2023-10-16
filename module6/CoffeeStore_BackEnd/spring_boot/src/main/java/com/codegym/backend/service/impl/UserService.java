package com.codegym.backend.service.impl;

import com.codegym.backend.dto.*;
import com.codegym.backend.model.Account;
import com.codegym.backend.model.User;
import com.codegym.backend.repository.IUserRepository;
import com.codegym.backend.service.IAccountServiceBao;
import com.codegym.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAccountServiceBao accountServiceBao;
    @Autowired
    private RoleService roleService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * ThangLV
     * get information of User by Username
     */
    @Override
    public IUserInforDTO findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<IUserDto> findAllUser() {
        return userRepository.findAllUser();
    }

    @Override
    public User findByAccountId(int accountId, Boolean enableFlag) {
        return userRepository.findByAccountId(accountId, enableFlag);
    }

    @Override
    public String findNameByAccountId(int accountId, Boolean enableFlag) {
        return userRepository.findNameByAccountId(accountId, enableFlag);
    }

    @Override
    public Page<IUserDto> findUserByNameOrDate(Pageable pageable, String date, String name) {
        return userRepository.findUserByNameOrDate(pageable, date, name);
    }

    @Override
    public Page<IUserDto> findUserByName(Pageable pageable, String name) {
        return userRepository.findUserByName(pageable, name);
    }

    @Override
    public Page<IUserDto> findAll(Pageable pageable) {
        return userRepository.findAllList(pageable);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserFindIdDTO getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void editUser(UserEditDTO userEditDTO, int id) throws MessagingException  {
        userRepository.editUser(
                userEditDTO.getName(),
                userEditDTO.getAddress(),
                userEditDTO.getPhoneNumber(),
                userEditDTO.getBirthday(),
                userEditDTO.getGender(),
                userEditDTO.getSalary(),
                userEditDTO.getImgUrl(),
                userEditDTO.getPosition(),
                id);
        accountServiceBao.updateEmailAccount(userEditDTO.getEmail(),userEditDTO.getUsername());
    }

    @Override
    public void createNewUser(UserDTO userDTO) throws MessagingException {

        Account account = new Account();
        account.setUserName(userDTO.getUserName());
        account.setPassword(passwordEncoder.encode("ABCdef"));
        account.setEmail(userDTO.getEmail());
        account.setEnableFlag(true);
        String now = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        account.setChangePassworDate(now);
        accountServiceBao.addNew(account);
        Integer id = accountServiceBao.findIdUserByUserName(userDTO.getUserName());
        roleService.setDefaultRole(id, 1);
        userRepository.createNewUser(userDTO.getName(), userDTO.getAddress(), userDTO.getPhoneNumber(),
                userDTO.getBirthday(), userDTO.getGender(), userDTO.getSalary(), userDTO.getImgUrl(), userDTO.getPosition(),id,false);
    }

    @Override
    public Integer findByPhone(String phoneNumber) {
        return userRepository.findByPhone(phoneNumber);
    }

    @Override
    public Integer findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Integer findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

package com.codegym.backend.controller;


import com.codegym.backend.dto.AccountDTO;
import com.codegym.backend.dto.IUserDto;

import com.codegym.backend.dto.IUserInforDTO;
import com.codegym.backend.payload.response.MessageResponse;

import com.codegym.backend.service.IAccountService;
import com.codegym.backend.service.IAccountServiceBao;
import com.codegym.backend.service.IUserService;
import com.codegym.backend.service.impl.AccountDetailServiceImpl;
import com.codegym.backend.validation.PasswordChangeValidator;


import com.codegym.backend.dto.AccountListDTO;
import com.codegym.backend.dto.UserDTO;
import com.codegym.backend.dto.UserEditDTO;
import com.codegym.backend.dto.UserFindIdDTO;

import com.codegym.backend.model.Position;

import com.codegym.backend.service.IPositionService;
import com.codegym.backend.validation.UserCreateByRequestDtoValidator;
import com.codegym.backend.validation.UserEditByRequestDtoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/private")

public class UserResController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountServiceBao accountServiceBao;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AccountDetailServiceImpl accountDetailService;
    @Autowired
    private PasswordChangeValidator passwordChangeValidator;
    @Autowired
    private IPositionService positionService;
    @Autowired
    private UserCreateByRequestDtoValidator userCreateByRequestDtoValidator;
    @Autowired
    private UserEditByRequestDtoValidator userEditByRequestDtoValidator;

    /**
     * ThangLV
     * get information of User by Username
     */
    @GetMapping("/find-user-infor")
    public ResponseEntity<IUserInforDTO> findUserInformation() {
        String username = accountDetailService.getCurrentUserName();
        IUserInforDTO user = userService.findUserByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * ThangLV
     * validation password, check password and change password
     */
    @PostMapping("/change-password-request")
    public ResponseEntity<Object> changePassword(@RequestBody AccountDTO accountDTO, BindingResult
            bindingResult) {
        passwordChangeValidator.validate(accountDTO, bindingResult);
        String username = accountDetailService.getCurrentUserName();
        accountDTO.setUserName(username);
        if (bindingResult.hasErrors()) {
            List<String> message = new ArrayList<>();
            bindingResult.getAllErrors().forEach(e -> message.add(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(message);
        }
        boolean checkPassword = accountService.checkPassword(accountDTO.getCurrentPassword(), accountDTO.getUserName());
        if (checkPassword) {
            accountService.changePassword(encoder.encode(accountDTO.getNewPassword()), accountDTO.getUserName());
            return ResponseEntity.ok(new MessageResponse("Đổi mật khẩu thành công"));
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Mật khẩu hiện tại không đúng"));
    }


    @PutMapping("/edit-user/{id}")
    public ResponseEntity<?> editUser(@Valid @RequestBody UserEditDTO userEditDTO, BindingResult
            bindingResult, @PathVariable int id) throws MessagingException {
        userEditByRequestDtoValidator.validate(userEditDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        userService.editUser(userEditDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> createNewUser() {
        List<Position> positionList = positionService.getAllPosition();
        List<Object> list = Arrays.asList(positionList);
        return new ResponseEntity<List<Object>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserDTO userDTO, BindingResult
            bindingResult) throws MessagingException {
        userCreateByRequestDtoValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        System.out.println(userDTO.toString());
        userService.createNewUser(userDTO);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/position")
    public ResponseEntity<List<Position>> getAllPosition() {
        List<Position> positionList = positionService.getAllPosition();
        if (positionList.isEmpty()) {
            return new ResponseEntity<List<Position>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Position>>(positionList, HttpStatus.OK);
    }

    @GetMapping("/account")
    public ResponseEntity<List<AccountListDTO>> getAllAccounts() {
        return new ResponseEntity<List<AccountListDTO>>(accountServiceBao.findAllAccount(), HttpStatus.OK);
    }


    @GetMapping("/find-id/{id}")
    public ResponseEntity<UserFindIdDTO> getById(@PathVariable Integer id) {
        System.out.println(id);
        UserFindIdDTO user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity<UserFindIdDTO>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<UserFindIdDTO>(user, HttpStatus.OK);
    }


    @GetMapping("/listUser")
    public ResponseEntity<Page<IUserDto>> getUserlist(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<IUserDto> userList = userService.findAll(pageable);
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/getUserByNameOrBirthday")
    public ResponseEntity<Page<IUserDto>> getListByNameOrDate(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String date, @RequestParam(defaultValue = "") String name) {
        Pageable pageable = PageRequest.of(page, size);
        Page<IUserDto> userList;
        if (Objects.equals(date, "")) {
            userList = userService.findUserByName(pageable, "%" + name + "%");
        } else {
            userList = userService.findUserByNameOrDate(pageable, date, "%" + name + "%");
        }
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userList);
    }

    @PutMapping("/userDelete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        List<IUserDto> userList = userService.findAllUser();
        if (userList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy tài khoản cần xóa!");
        } else if (Objects.equals(userList.get(id - 1).getEnableFlag(), "false")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy tài khoản cần xóa!");
        } else {
            userService.deleteById(id);
            return ResponseEntity.ok("Đã xóa thành công!");
        }
    }
}

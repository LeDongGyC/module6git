package com.codegym.backend.validation;

import com.codegym.backend.dto.AccountDTO;
import com.codegym.backend.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class PasswordChangeValidator implements Validator {

    @Autowired
    AccountService accountService;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    private String fieldNewPassword = "newPassword";
    private String patternNewPassword = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[#$@!%&*?-_])[A-Za-z#$@!%&*?-_]{6,15}$";

    @Override
    public void validate(Object target, Errors errors) {
        AccountDTO accountDTO = (AccountDTO) target;
        if (accountDTO.getCurrentPassword().equals("")){
            errors.rejectValue("currentPassword", "currentPassword.null","Mật khẩu hiện tại không được để trống");
        } else if (accountDTO.getCurrentPassword().length() < 6 || accountDTO.getCurrentPassword().length() > 15){
            errors.rejectValue("currentPassword", "currentPassword.length","Mật khẩu hiện tại từ 6 đến 15 ký tự");
        }
        if (accountDTO.getNewPassword().equals("")){
            errors.rejectValue(fieldNewPassword, "newPassword.null","Mật khẩu mới không được để trống");
        }else if (!Pattern.compile(patternNewPassword).matcher(accountDTO.getNewPassword()).find()){
            errors.rejectValue(fieldNewPassword, "newPassword.pattern", "Mật khẩu mới từ 6 -15 ký tự, gồm chữ thường, chữ hoa, ký tự đặc biệt");
        } else if (accountDTO.getCurrentPassword().equals(accountDTO.getNewPassword())){
            errors.rejectValue(fieldNewPassword, "newPassword.checkMatches", "Mật khẩu mới không được trùng mật khẩu hiện tại");
        }
    }
}

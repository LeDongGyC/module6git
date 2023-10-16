package com.codegym.backend.validation;


import com.codegym.backend.dto.UserDTO;
import com.codegym.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.regex.Pattern;


@Component
public class UserCreateByRequestDtoValidator implements Validator {
    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        Date date = new Date();

        String fiedldUserName = "userName";
        String fieldEmail = "email";
        String fieldPhoneNumber = "phoneNumber";
        String fieldAddress = "address";
        String regexAddress = "^[a-zA-Z0-9\'-\'\\sáàảãạăâắằấầặẵẫậéèẻ ẽẹếềểễệóêòỏõọôốồổỗộ ơớờởỡợíìỉĩịđùúủũụưứ� �ửữựÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠ ƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼ� ��ỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞ ỠỢỤỨỪỬỮỰỲỴÝỶỸửữựỵ ỷỹ ,]*$";
        String regexName = "^[a-zA-Z\'-\'\\sáàảãạăâắằấầặẵẫậéèẻ ẽẹếềểễệóêòỏõọôốồổỗộ ơớờởỡợíìỉĩịđùỳúủũụưứ� �ửữựÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠ ƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼ� ��ỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞ ỠỢỤỨỪỬỮỰỲỴÝỶỸửữựỵ ỷỹ]*$";
        String[] dateSplit = userDTO.getBirthday().split("-");
        dateSplit[0] = String.valueOf(Integer.parseInt(dateSplit[0]) - 1900);
        Date birthday = new Date(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]) - 1, Integer.parseInt(dateSplit[2]));
        if (userDTO.getUserName() == null) {
            errors.rejectValue(fiedldUserName, "userName.null", "Vui lòng nhập tên tài khoản.");
        }else if (userService.findByUserName(userDTO.getUserName()) > 0) {
            errors.rejectValue(fiedldUserName, "userName.duplicate", "Tên tài khoản đã tồn tại.");
        }else if (!Pattern.compile("^[a-z0-9]+$").matcher(userDTO.getUserName()).find()) {
            errors.rejectValue(fiedldUserName, "userName.format", "Không được nhập ký tự đặt biệt.");
        }
        if (userDTO.getName() == null) {
            errors.rejectValue("name", "name.null", "Vui lòng nhập tên.");
        } else if (userDTO.getName().length() < 6) {
            errors.rejectValue("name", "name.length", "Tên phải lớn hơn 6 ký tự");
        } else if (userDTO.getName().length() > 40) {
            errors.rejectValue("name", "name.length", "Tên phải bé hơn 40 ký tự");
        } else if (!Pattern.compile(regexName).matcher(userDTO.getName()).find()) {
            errors.rejectValue("name", "name.pattern", "Không được nhập ký tự đặt biệt hoặc số.");
        }
        if (userDTO.getGender() == null) {
            errors.rejectValue("gender", "gender.null", "Vui lòng nhập giới tính.");
        }
        if (userDTO.getBirthday() == null) {
            errors.rejectValue("birthday", "birthday.null", "Ngày sinh không được để trống");
        } else if (((date.getTime()) - (birthday.getTime())) / 1000 / 60 / 60 / 24 < 6570 || ((date.getTime()) - (birthday.getTime())) / 1000 / 60 / 60 / 24 > 18250) {
            errors.rejectValue("birthday", "birthday.format", "Ngày sinh phải từ 18 tuổi đến 50 tuổi");
        }
        if (userDTO.getEmail() == null) {
            errors.rejectValue(fieldEmail, "email.null", "Vui lòng nhập email.");
        } else if (!Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(userDTO.getEmail()).find()) {
            errors.rejectValue(fieldEmail, "email.format", "Email tuân thủ theo format ex: abc@gmail.com");
        } else if (userService.findByEmail(userDTO.getEmail()) > 0) {
            errors.rejectValue(fieldEmail, "email.duplicate", "Email đã tồn tại");
        }
        if (userDTO.getAddress().length() > 100) {
            errors.rejectValue(fieldAddress, "address.length", "Địa chỉ phải nhỏ hơn 100 ký tự");
        } else if (userDTO.getAddress() == null) {
            errors.rejectValue(fieldAddress, "address.null", "Vui lòng nhập địa chỉ.");
        } else if (!Pattern.compile(regexAddress).matcher(userDTO.getAddress()).find()) {
            errors.rejectValue(fieldAddress, "address.format", "Không được nhập ký tự đặt biệt.");
        }
        if (userDTO.getPhoneNumber() == null) {
            errors.rejectValue(fieldPhoneNumber, "phoneNumber.null", "Số địa thoại không được để trống");
        } else if (!Pattern.compile("^(\\+?84|0)(3[2-9]|5[689]|7[0|6-9]|8[1-9]|9[0-9])[0-9]{7}$").matcher(userDTO.getPhoneNumber()).find()) {
            errors.rejectValue(fieldPhoneNumber, "phoneNumber.format", "Vui lòng nhập số điện thoại đúng định dạng 09xxxxxxx, 03xxxxxxx, 07xxxxxxx, (84) + 90xxxxxxx.");
        } else if (userService.findByPhone(userDTO.getPhoneNumber()) > 0) {
            errors.rejectValue(fieldPhoneNumber, "phoneNumber.duplicate", "Số điện thoại đã tồn tại");
        }
        if (userDTO.getPosition() == null){
            errors.rejectValue("position", "position.null", "Vui lòng chọn chức vụ.");
        }
        if (userDTO.getSalary() == null){
            errors.rejectValue("salary", "salary.null", "Vui lòng nhập lương.");
        }
    }
}
package com.codegym.backend.validation;


import com.codegym.backend.dto.UserEditDTO;
import com.codegym.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.regex.Pattern;

@Component
public class UserEditByRequestDtoValidator implements Validator {
    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserEditDTO userEditDTO = (UserEditDTO) target;
        Date date = new Date();
        String[] dateSplit = userEditDTO.getBirthday().split("-");
        dateSplit[0] = String.valueOf(Integer.parseInt(dateSplit[0]) - 1900);
        Date birthday = new Date(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]) - 1, Integer.parseInt(dateSplit[2]));
        if (userEditDTO.getName() == null) {
            errors.rejectValue("name", "name.null", "Vui lòng nhập tên.");
        } else if (userEditDTO.getName().length() < 6) {
            errors.rejectValue("name", "name.length", "Tên phải lớn hơn 6 ký tự");
        } else if (userEditDTO.getName().length() > 40) {
            errors.rejectValue("name", "name.length", "Tên phải bé hơn 40 ký tự");
        } else if (!Pattern.compile("^[a-zA-Z\'-\'\\sáàảãạăâắằấầặẵẫậéèẻ ẽẹếềểễệóêòỏõọôốồổỗộ ơớờởỡợíìỉĩịđùỳúủũụưứ� �ửữựÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠ ƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼ� ��ỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞ ỠỢỤỨỪỬỮỰỲỴÝỶỸửữựỵ ỷỹ]*$").matcher(userEditDTO.getName()).find()) {
            errors.rejectValue("name", "name.pattern", "Không được nhập ký tự đặt biệt hoặc số.");
        }
        if (userEditDTO.getGender() == null) {
            errors.rejectValue("gender", "gender.null", "Vui lòng nhập giới tính.");
        }
        if (userEditDTO.getBirthday() == null) {
            errors.rejectValue("birthday", "birthday.null", "Ngày sinh không được để trống");
        } else if (((date.getTime()) - (birthday.getTime())) / 1000 / 60 / 60 / 24 < 6570 || ((date.getTime()) - (birthday.getTime())) / 1000 / 60 / 60 / 24 > 25550) {
            errors.rejectValue("birthday", "birthday.format", "Ngày sinh phải từ 18 tuổi đến 70 tuổi");
        }
        if (userEditDTO.getEmail() == null) {
            errors.rejectValue("email", "email.null", "Vui lòng nhập email.");
        } else if (!Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(userEditDTO.getEmail()).find()) {
            errors.rejectValue("email", "email.format", "Email tuân thủ theo format ex: abc@gmail.com");
        } else if (userService.findByEmail(userEditDTO.getEmail()) > 0) {
            errors.rejectValue("email", "email.duplicate", "Email đã tồn tại");
        }
        if (userEditDTO.getAddress().length() > 100) {
            errors.rejectValue("address", "address.length", "Địa chỉ phải nhỏ hơn 100 ký tự");
        } else if (userEditDTO.getAddress() == null) {
            errors.rejectValue("address", "address.null", "Vui lòng nhập địa chỉ.");
        } else if (!Pattern.compile("^[a-zA-Z0-9\'-\'\\sáàảãạăâắằấầặẵẫậéèẻ ẽẹếềểễệóêòỏõọôốồổỗộ ơớờởỡợíìỉĩịđùúủũụưứ� �ửữựÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠ ƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼ� ��ỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞ ỠỢỤỨỪỬỮỰỲỴÝỶỸửữựỵ ỷỹ ,]*$").matcher(userEditDTO.getAddress()).find()) {
            errors.rejectValue("address", "address.format", "Không được nhập ký tự đặt biệt.");
        }
        if (userEditDTO.getPhoneNumber() == null) {
            errors.rejectValue("phoneNumber", "phoneNumber.null", "Số địa thoại không được để trống");
        } else if (!Pattern.compile("^(\\+?84|0)(3[2-9]|5[689]|7[0|6-9]|8[1-9]|9[0-9])[0-9]{7}$").matcher(userEditDTO.getPhoneNumber()).find()) {
            errors.rejectValue("phoneNumber", "phoneNumber.format", "Vui lòng nhập số điện thoại đúng định dạng 09xxxxxxx, 03xxxxxxx, 07xxxxxxx, (84) + 90xxxxxxx.");
        } else if (userService.findByPhone(userEditDTO.getPhoneNumber()) > 0) {
            errors.rejectValue("phoneNumber", "phoneNumber.duplicate", "Số điện thoại đã tồn tại");
        }
        if (userEditDTO.getPosition() == null) {
            errors.rejectValue("position", "position.null", "Vui lòng chọn chức vụ.");
        }
        if (userEditDTO.getSalary() == null) {
            errors.rejectValue("salary", "salary.null", "Vui lòng nhập lương.");
        }
    }
}

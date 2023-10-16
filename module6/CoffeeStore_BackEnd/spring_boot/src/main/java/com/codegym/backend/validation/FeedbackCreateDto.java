package com.codegym.backend.validation;

import com.codegym.backend.dto.CreateFeedback;
import com.codegym.backend.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class FeedbackCreateDto implements Validator {
    @Autowired
    private IFeedbackService feedbackService;
    String regexName = "^[a-zA-Z\'-\'\\sáàảãạăâắằấầặẵẫậéèẻ ẽẹếềểễệóêòỏõọôốồổỗộ ơớờởỡợíìỉĩịđùúủũụưứửữự ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠ ƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼ ỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞ ỠỢỤỨỪỬỮỰỲỴÝỶỸửữựỵ ỷỹ]*$";
    String regexEmail = "[a-zA-Z][a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
    String nameField = "name";
    String emailField = "email";
    String contentField = "content";

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateFeedback createFeedback = (CreateFeedback) target;
        String name = createFeedback.getName();
        String email = createFeedback.getEmail();
        String content = createFeedback.getContent();
        Integer count = feedbackService.countEmail(email);
        if (name.equals("")) {
            errors.rejectValue(nameField, "name.null", "Tên không được phép trống");
        } else if (!Pattern.compile(regexName).matcher(name).find()) {
            errors.rejectValue(nameField, "name.pattern", "Tên không chứa số hoặc kí tự đặc biệt");
        } else if (name.length() < 5) {
            errors.rejectValue(nameField, "name.length", "Tên phải dài hơn 5 kí tự");
        } else if (name.length() > 50) {
            errors.rejectValue(nameField, "name.length", "Tên không được dài hơn 50 kí tự");
        } else if (name.trim().equals("")) {
            errors.rejectValue(nameField, "name.space", "Tên không được chứa toàn kí tự trắng");
        }
        if (email.equals("")) {
            errors.rejectValue(emailField, "email.null", "Email không được phép trống");
        } else if (!Pattern.compile(regexEmail).matcher(email).find()) {
            errors.rejectValue(emailField, "email.pattern", "Email không đúng định dạng");
        } else if (email.length() < 10) {
            errors.rejectValue(emailField, "email.length", "Tên phải dài hơn 10 kí tự");
        } else if (email.length() > 254) {
            errors.rejectValue(emailField, "email.length", "Tên không được dài hơn 254 kí tự");
        } else if (count > 0) {
            errors.rejectValue(emailField, "email.duplicate", "Đã tồn tài email");
        }
        if (content.equals("")) {
            errors.rejectValue(contentField, "content.null", "Nội dung phản hồi không được phép trống");
        } else if (content.length() < 10) {
            errors.rejectValue(contentField, "content.length", "Nội dung phản hồi dài hơn 10 kí tự");
        } else if (content.length() > 1000) {
            errors.rejectValue(contentField, "content.length", "Nội dung phản hồi nhỏ hơn 1000 kí tự");
        }
    }
}


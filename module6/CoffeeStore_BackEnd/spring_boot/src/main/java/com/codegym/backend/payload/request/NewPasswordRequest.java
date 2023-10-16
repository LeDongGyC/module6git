package com.codegym.backend.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class NewPasswordRequest {
    @NotBlank(message = "Không được để trống mật khẩu")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[#$@!%&*?-_])[A-Za-z#$@!%&*?-_]{6,15}$",
            message = "Mật khẩu tối thiểu 6-15 ký tự bao gồm chữ in hoa và chữ thường không dấu và ký tự đặc biệt!")
    private String newPassword;
    private String code;

    public NewPasswordRequest() {
    }

    public NewPasswordRequest(String newPassword, String code) {
        this.newPassword = newPassword;
        this.code = code;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

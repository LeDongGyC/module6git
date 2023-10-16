package com.codegym.backend.payload.request;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginRequest {
    @NotBlank(message = "Tên tài khoản không được để trống")
    private String username;

    @NotBlank(message = "Không được để trống mật khẩu")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[#$@!%&*?-_])[A-Za-z#$@!%&*?-_]{6,15}$",
            message = "Mật khẩu tối thiểu 6-15 ký tự bao gồm chữ in hoa và chữ thường không dấu và ký tự đặc biệt!")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

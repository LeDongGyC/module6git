package com.codegym.backend.payload.request;

public class VerificationCodeRequest {
    private String code;

    public VerificationCodeRequest() {
    }

    public VerificationCodeRequest(String verifiactionCode) {
        this.code = verifiactionCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

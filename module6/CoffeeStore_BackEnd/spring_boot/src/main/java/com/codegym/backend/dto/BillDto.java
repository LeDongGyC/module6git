package com.codegym.backend.dto;

public interface BillDto {
    public Integer getId();
    public String getCreatedTime();
    public Boolean getPaymentStatus();
    public String getPaymentTime();
    public Integer getTableId();
    public Integer getUserId();
}

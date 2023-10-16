package com.codegym.backend.dto;

public class BillInsertDTO {
    private int payment_status;
    private String payment_time;
    private int table_id;
    private int user_id;

    public BillInsertDTO(int payment_status, String payment_time, int table_id, int user_id) {
        this.payment_status = payment_status;
        this.payment_time = payment_time;
        this.table_id = table_id;
        this.user_id = user_id;
    }

    public BillInsertDTO() {
    }
    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
    }

    public String getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(String payment_time) {
        this.payment_time = payment_time;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
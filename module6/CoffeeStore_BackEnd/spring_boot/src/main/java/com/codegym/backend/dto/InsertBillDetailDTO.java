package com.codegym.backend.dto;

public class InsertBillDetailDTO {
    private int quantity;
    private int bill_id;
    private int service_id;

    public InsertBillDetailDTO() {
    }

    public InsertBillDetailDTO(int quantity, int bill_id, int service_id) {
        this.quantity = quantity;
        this.bill_id = bill_id;
        this.service_id = service_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }
}
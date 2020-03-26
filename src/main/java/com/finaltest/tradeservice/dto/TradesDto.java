package com.finaltest.tradeservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class TradesDto {

    private String customerEmail;
    private String paymentMethod;
    private int paymentAmount;
    private String discount;

    @JsonProperty("listItem")
    private List<Map<String, Object>> listItem;

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<Map<String, Object>> getListItem() {
        return listItem;
    }

    public void setListItem(List<Map<String, Object>> listItem) {
        this.listItem = listItem;
    }
}

package com.finaltest.tradeservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "trades")
public class TradesModel extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String transactionDate;

    @Column
    @NotNull
    private String customerEmail;

    @Column
    private String paymentMethod;

    @Column
    @NotNull
    private Long totalBruto;

    @Column
    private Long tax;

    @Column
    private Long serviceFee;

    @Column
    private Long discountAmpunt;

    @Column
    private Long transactionNumber;

    @Column
    private Long totalPayment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
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

    public Long getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(Long totalBruto) {
        this.totalBruto = totalBruto;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public Long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Long serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Long getDiscountAmpunt() {
        return discountAmpunt;
    }

    public void setDiscountAmpunt(Long discountAmpunt) {
        this.discountAmpunt = discountAmpunt;
    }

    public Long getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Long transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Long getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Long totalPayment) {
        this.totalPayment = totalPayment;
    }
}

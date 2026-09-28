package com.project.payment_processor_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankRequest {

    private String paymentId;
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;

    public BankRequest() {
    }

    public BankRequest(
            String paymentId,
            BigDecimal amount,
            String currency,
            String paymentMethod) {

        this.paymentId = paymentId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
    }

}

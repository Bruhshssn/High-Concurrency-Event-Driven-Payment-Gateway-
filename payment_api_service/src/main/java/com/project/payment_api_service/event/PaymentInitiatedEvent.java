package com.project.payment_api_service.event;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentInitiatedEvent {

    private String paymentId;
    private Long userId;
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private String idempotencyKey;

    public PaymentInitiatedEvent() {
    }

    public PaymentInitiatedEvent(
            String paymentId,
            Long userId,
            BigDecimal amount,
            String currency,
            String paymentMethod,
            String idempotencyKey) {

        this.paymentId = paymentId;
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.idempotencyKey = idempotencyKey;
    }

}
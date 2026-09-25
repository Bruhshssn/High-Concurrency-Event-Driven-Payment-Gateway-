package com.project.payment_processor_service.event;

import lombok.Data;

@Data
public class PaymentInitiatedEvent {

    private String paymentId;
    private Long userId;
    private Double amount;

    public PaymentInitiatedEvent() {
    }

    public PaymentInitiatedEvent(String paymentId, Long userId, Double amount) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.amount = amount;
    }

}
package com.project.payment_api_service.event;

import lombok.Data;

@Data
public class PaymentStatusUpdateEvent {
    private String paymentId;
    private String status;

    public PaymentStatusUpdateEvent() {
    }

    public PaymentStatusUpdateEvent(String paymentId, String status) {
        this.paymentId = paymentId;
        this.status = status;
    }
}

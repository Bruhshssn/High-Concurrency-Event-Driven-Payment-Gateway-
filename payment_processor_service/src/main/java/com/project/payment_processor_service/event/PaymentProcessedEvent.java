package com.project.payment_processor_service.event;

import com.project.payment_processor_service.enumerated.PaymentStatus;
import lombok.Data;

@Data
public class PaymentProcessedEvent {

    private String paymentId;
    private PaymentStatus status;
    private String transactionId;
    private String message;

    public PaymentProcessedEvent() {
    }

    public PaymentProcessedEvent(
            String paymentId,
            PaymentStatus status,
            String transactionId,
            String message) {

        this.paymentId = paymentId;
        this.status = status;
        this.transactionId = transactionId;
        this.message = message;
    }
}

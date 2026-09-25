package com.project.payment_api_service.dto;

import lombok.Data;

@Data
public class PaymentResponse {

    private String paymentId;
    private String status;
    private String message;

    public PaymentResponse(String paymentId, String status, String message) {
        this.paymentId = paymentId;
        this.status = status;
        this.message = message;
    }
}
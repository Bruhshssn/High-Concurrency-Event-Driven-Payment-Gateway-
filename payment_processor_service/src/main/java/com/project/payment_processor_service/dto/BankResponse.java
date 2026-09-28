package com.project.payment_processor_service.dto;

import lombok.Data;

@Data
public class BankResponse {

    private boolean success;
    private String transactionId;
    private String message;

    public BankResponse() {
    }

    public BankResponse(
            boolean success,
            String transactionId,
            String message) {

        this.success = success;
        this.transactionId = transactionId;
        this.message = message;
    }

}

package com.project.payment_api_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {

    private Long userId;

    private BigDecimal amount;

    private String currency;

    private String paymentMethod;

}

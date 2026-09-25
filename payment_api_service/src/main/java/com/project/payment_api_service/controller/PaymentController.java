package com.project.payment_api_service.controller;

import com.project.payment_api_service.dto.PaymentRequest;
import com.project.payment_api_service.dto.PaymentResponse;
import com.project.payment_api_service.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestHeader("Idempotency-Key") String idempotencyKey, @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.initiatePayment(request, idempotencyKey);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable String paymentId) {

        return ResponseEntity.ok().build();
    }
}

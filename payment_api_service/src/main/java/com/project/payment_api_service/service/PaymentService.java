package com.project.payment_api_service.service;

import com.project.payment_api_service.dto.PaymentRequest;
import com.project.payment_api_service.dto.PaymentResponse;
import com.project.payment_api_service.entity.Payment;
import com.project.payment_api_service.enumerated.PaymentStatus;
import com.project.payment_api_service.event.PaymentEventProducer;
import com.project.payment_api_service.event.PaymentInitiatedEvent;
import com.project.payment_api_service.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventProducer eventProducer;

    public PaymentService(
            PaymentRepository paymentRepository,
            PaymentEventProducer eventProducer) {

        this.paymentRepository = paymentRepository;
        this.eventProducer = eventProducer;
    }

    @Transactional
    public PaymentResponse initiatePayment(
            PaymentRequest request,
            String idempotencyKey) {

        Optional<Payment> existingPayment =
                paymentRepository.findByIdempotencyKey(idempotencyKey);

        if (existingPayment.isPresent()) {

            Payment payment = existingPayment.get();

            return new PaymentResponse(
                    payment.getPaymentId(),
                    payment.getStatus().name(),
                    "Payment already exists"
            );
        }

        Payment payment = new Payment();

        String paymentId =
                "PAY-" + UUID.randomUUID();

        payment.setPaymentId(paymentId);
        payment.setUserId(request.getUserId());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setIdempotencyKey(idempotencyKey);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        paymentRepository.save(payment);

        PaymentInitiatedEvent event =
                new PaymentInitiatedEvent(
                        paymentId,
                        request.getUserId(),
                        request.getAmount(),
                        request.getCurrency(),
                        request.getPaymentMethod(),
                        idempotencyKey
                );

        eventProducer.publishPayment(event);

        return new PaymentResponse(paymentId,
                "PENDING",
                "Payment accepted for processing"
        );
    }
}
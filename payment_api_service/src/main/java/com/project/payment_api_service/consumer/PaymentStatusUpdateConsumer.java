package com.project.payment_api_service.consumer;

import com.project.payment_api_service.entity.Payment;
import com.project.payment_api_service.enumerated.PaymentStatus;
import com.project.payment_api_service.event.PaymentStatusUpdateEvent;
import com.project.payment_api_service.repository.PaymentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentStatusUpdateConsumer {

    private final PaymentRepository paymentRepository;

    public PaymentStatusUpdateConsumer(
            PaymentRepository paymentRepository) {

        this.paymentRepository = paymentRepository;
    }

    @KafkaListener(
            topics = "payment-status-updates",
            groupId = "payment-api-group",
            containerFactory = "paymentStatusKafkaListenerContainerFactory"
    )
    public void consume(PaymentStatusUpdateEvent event) {

        Payment payment = paymentRepository
                        .findByPaymentId(event.getPaymentId())
                        .orElseThrow();

        payment.setStatus(PaymentStatus.valueOf(event.getStatus()));

        payment.setUpdatedAt(LocalDateTime.now());

        paymentRepository.save(payment);
    }
}
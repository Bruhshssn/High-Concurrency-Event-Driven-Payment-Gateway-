package com.project.payment_api_service.event;

import com.project.payment_api_service.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessedEventConsumer {

    private final PaymentService paymentService;

    public PaymentProcessedEventConsumer(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @KafkaListener(
            topics = "payment-processed",
            groupId = "payment-api-group"
    )
    public void consume(PaymentProcessedEvent event) {

        paymentService.updatePaymentStatus(event);
    }
}

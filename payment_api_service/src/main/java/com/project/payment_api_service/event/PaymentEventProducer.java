package com.project.payment_api_service.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventProducer {

    private final KafkaTemplate<String, PaymentInitiatedEvent> kafkaTemplate;

    public PaymentEventProducer(KafkaTemplate<String, PaymentInitiatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPayment(PaymentInitiatedEvent event) {

        kafkaTemplate.send("payment-initiated", event.getPaymentId(), event);
    }
}

package com.project.payment_processor_service.producer;

import com.project.payment_processor_service.event.PaymentStatusUpdateEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentStatusProducer {

    private static final String TOPIC = "payment-status-updates";

    private final KafkaTemplate<String, PaymentStatusUpdateEvent> kafkaTemplate;

    public PaymentStatusProducer(
            KafkaTemplate<String, PaymentStatusUpdateEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStatusUpdate(PaymentStatusUpdateEvent event) {

        kafkaTemplate.send(
                TOPIC,
                String.valueOf(event.getPaymentId()),
                event
        );
    }
}
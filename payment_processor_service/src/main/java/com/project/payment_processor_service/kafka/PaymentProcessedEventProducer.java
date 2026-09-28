package com.project.payment_processor_service.kafka;

import com.project.payment_processor_service.event.PaymentProcessedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessedEventProducer {

    private final KafkaTemplate<String, PaymentProcessedEvent>
            kafkaTemplate;

    public PaymentProcessedEventProducer(
            KafkaTemplate<String, PaymentProcessedEvent>
                    kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(PaymentProcessedEvent event) {

        kafkaTemplate.send(
                "payment-processed",
                event.getPaymentId(),
                event
        );
    }
}

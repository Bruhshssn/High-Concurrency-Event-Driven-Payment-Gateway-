package com.project.payment_processor_service.kafka;

import com.project.payment_processor_service.service.PaymentProcessorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    private final PaymentProcessorService paymentProcessorService;

    public PaymentConsumer(
            PaymentProcessorService paymentProcessorService) {

        this.paymentProcessorService =
                paymentProcessorService;
    }

    @KafkaListener(
            topics = "payment-initiated",
            groupId = "payment-processor-group"
    )
    public void consume(PaymentInitiatedEvent event) {

        paymentProcessorService.process(event);
    }
}

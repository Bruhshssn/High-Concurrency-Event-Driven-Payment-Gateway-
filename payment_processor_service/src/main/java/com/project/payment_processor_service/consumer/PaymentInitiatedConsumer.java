package com.project.payment_processor_service.consumer;

import com.project.payment_processor_service.event.PaymentInitiatedEvent;
import com.project.payment_processor_service.service.PaymentProcessorService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentInitiatedConsumer {

    private final PaymentProcessorService paymentProcessorService;

    public PaymentInitiatedConsumer(
            PaymentProcessorService paymentProcessorService) {

        this.paymentProcessorService = paymentProcessorService;
    }

    @KafkaListener(
            topics = "payment-initiated",
            groupId = "payment-processor-group"
    )
    public void consume(PaymentInitiatedEvent event) {

        System.out.println(
                "Received payment initiated event: "
                        + event.getPaymentId()
        );

        paymentProcessorService.process(event);
    }
}

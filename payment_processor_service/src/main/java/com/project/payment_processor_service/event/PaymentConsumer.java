package com.project.payment_processor_service.event;

import com.project.payment_processor_service.service.PaymentProcessorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    private final PaymentProcessorService processorService;

    public PaymentConsumer(
            PaymentProcessorService processorService) {
        this.processorService = processorService;
    }

    @KafkaListener(
            topics = "payment-initiated",
            groupId = "payment-processor"
    )
    public void consume(PaymentInitiatedEvent event) {

        processorService.process(event);
    }
}
package com.project.payment_processor_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, PaymentInitiatedEvent>
    consumerFactory(
            ConsumerFactory<String, PaymentInitiatedEvent> factory) {
        return factory;
    }
}

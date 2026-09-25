package com.project.payment_processor_service.service;

import com.project.payment_processor_service.event.PaymentInitiatedEvent;
import com.project.payment_processor_service.event.PaymentStatusUpdateEvent;
import com.project.payment_processor_service.producer.PaymentStatusProducer;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class PaymentProcessorService {

    private final RedisTemplate<String, String> redisTemplate;
    private final PaymentStatusProducer paymentStatusProducer;

    public PaymentProcessorService(
            RedisTemplate<String, String> redisTemplate,
            PaymentStatusProducer paymentStatusProducer) {

        this.redisTemplate = redisTemplate;
        this.paymentStatusProducer = paymentStatusProducer;
    }

    public void process(PaymentInitiatedEvent event) {

        String lockKey =
                "payment-lock:" + event.getPaymentId();

        Boolean acquired =
                redisTemplate.opsForValue().setIfAbsent(
                        lockKey,
                        "LOCKED",
                        Duration.ofSeconds(30)
                );

        // Another processor already processing this payment
        if (!Boolean.TRUE.equals(acquired)) {
            return;
        }

        try {

            System.out.println(
                    "Processing payment: " + event.getPaymentId()
            );

            /*
             * Mock bank processing will be added here later.
             *
             * For now, assume payment succeeds.
             */

            String status = "SUCCESS";

            PaymentStatusUpdateEvent statusEvent =
                    new PaymentStatusUpdateEvent(
                            event.getPaymentId(),
                            status
                    );

            paymentStatusProducer.sendStatusUpdate(statusEvent);

        } catch (Exception e) {

            PaymentStatusUpdateEvent failedEvent =
                    new PaymentStatusUpdateEvent(
                            event.getPaymentId(),
                            "FAILED"
                    );

            paymentStatusProducer.sendStatusUpdate(failedEvent);

            throw e;

        } finally {

            redisTemplate.delete(lockKey);
        }
    }
}
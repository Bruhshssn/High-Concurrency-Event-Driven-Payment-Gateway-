package com.project.payment_processor_service.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class PaymentLockService {

    private final RedisTemplate<String, String> redisTemplate;

    public PaymentLockService(
            RedisTemplate<String, String> redisTemplate) {

        this.redisTemplate = redisTemplate;
    }

    public boolean acquireLock(String paymentId) {

        String key =
                "payment:lock:" + paymentId;

        Boolean acquired =
                redisTemplate.opsForValue()
                        .setIfAbsent(
                                key,
                                "LOCKED",
                                Duration.ofSeconds(30)
                        );

        return Boolean.TRUE.equals(acquired);
    }

    public void releaseLock(String paymentId) {

        String key =
                "payment:lock:" + paymentId;

        redisTemplate.delete(key);
    }
}
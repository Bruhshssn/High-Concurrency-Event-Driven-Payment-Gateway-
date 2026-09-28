package com.project.payment_processor_service.client;

import com.project.payment_processor_service.dto.BankRequest;
import com.project.payment_processor_service.dto.BankResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class MockBankClient {

    private final RestClient restClient;

    public MockBankClient(RestClient.Builder builder) {

        this.restClient = builder
                .baseUrl("http://localhost:8082")
                .build();
    }

    public BankResponse charge(BankRequest request) {

        return restClient
                .post()
                .uri("/mock-bank/charge")
                .body(request)
                .retrieve()
                .body(BankResponse.class);
    }
}
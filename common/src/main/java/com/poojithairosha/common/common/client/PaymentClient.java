package com.poojithairosha.common.common.client;

import com.poojithairosha.common.payment.dto.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(
        name = "payment-service",
        url = "http://localhost:8222/api/v1/payments"
)
public interface PaymentClient {

    @PostMapping
    Optional<Integer> createPayment(@RequestBody @Valid PaymentRequest request);

}

package com.poojithairosha.common.common.client;

import com.poojithairosha.common.customer.dto.CustomerRequest;
import com.poojithairosha.common.customer.dto.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "http://localhost:8222/api/v1/customers"
)
public interface CustomerClient {

    @PostMapping
    Optional<String> createCustomer(@RequestBody @Valid CustomerRequest request);

    @PutMapping
    Optional<Void> updateCustomer(@RequestBody @Valid CustomerRequest request);

    @GetMapping
    Optional<List<CustomerResponse>> findAll();

    @GetMapping("exists/{customer-id}")
    Optional<Boolean> existsById(@PathVariable("customer-id") String customerId);

    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findById(@PathVariable("customer-id") String customerId);

    @DeleteMapping("/{customer-id}")
    Optional<Void> delete(@PathVariable("customer-id") String customerId);

}

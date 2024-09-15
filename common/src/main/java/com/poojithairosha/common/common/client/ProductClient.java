package com.poojithairosha.common.common.client;

import com.poojithairosha.common.product.dto.ProductPurchaseRequest;
import com.poojithairosha.common.product.dto.ProductPurchaseResponse;
import com.poojithairosha.common.product.dto.ProductRequest;
import com.poojithairosha.common.product.dto.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8222/api/v1/products"
)
public interface ProductClient {

    @PostMapping
    Optional<Integer> createProduct(@RequestBody @Valid ProductRequest request);

    @PostMapping("/purchase")
    Optional<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> request);

    @GetMapping("/{product-id}")
    Optional<ProductResponse> findById(@PathVariable("product-id") Integer productId);

    @GetMapping
    Optional<List<ProductResponse>> findAll();

}

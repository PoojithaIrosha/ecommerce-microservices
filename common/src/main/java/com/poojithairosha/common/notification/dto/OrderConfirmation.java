package com.poojithairosha.common.notification.dto;

import com.poojithairosha.common.order.enums.PaymentMethod;
import com.poojithairosha.common.product.dto.ProductPurchaseResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail,
        List<ProductPurchaseResponse> products
) {
}

package com.poojithairosha.common.payment.dto;

import com.poojithairosha.common.customer.dto.CustomerResponse;
import com.poojithairosha.common.order.enums.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}

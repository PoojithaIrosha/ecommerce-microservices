package com.poojithairosha.common.order.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.poojithairosha.common.order.enums.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@JsonInclude(Include.NON_EMPTY)
public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {

}

package com.poojithairosha.common.order.dto;

import lombok.Builder;

@Builder
public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity
) {
}

package com.poojithairosha.order.mapper;

import com.poojithairosha.common.order.dto.OrderLineRequest;
import com.poojithairosha.common.order.dto.OrderLineResponse;
import com.poojithairosha.order.entity.Order;
import com.poojithairosha.order.entity.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {

    public OrderLine mapToEntity(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .productId(request.productId())
                .quantity(request.quantity())
                .order(Order.builder().id(request.orderId()).build())
                .build();
    }

    public OrderLineResponse mapToResponse(OrderLine orderLine) {
        return OrderLineResponse.builder()
                .id(orderLine.getId())
                .quantity(orderLine.getQuantity())
                .build();
    }
}

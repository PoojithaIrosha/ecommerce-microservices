package com.poojithairosha.order.mapper;

import com.poojithairosha.common.order.dto.OrderRequest;
import com.poojithairosha.common.order.dto.OrderResponse;
import com.poojithairosha.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order mapToEntity(OrderRequest orderRequest) {
        return Order.builder()
                .id(orderRequest.id())
                .customerId(orderRequest.customerId())
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.amount())
                .paymentMethod(orderRequest.paymentMethod())
                .build();
    }

    public OrderResponse mapToResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .reference(order.getReference())
                .amount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }

}

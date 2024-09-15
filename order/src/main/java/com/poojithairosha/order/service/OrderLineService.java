package com.poojithairosha.order.service;

import com.poojithairosha.common.order.dto.OrderLineRequest;
import com.poojithairosha.common.order.dto.OrderLineResponse;
import com.poojithairosha.order.entity.OrderLine;
import com.poojithairosha.order.mapper.OrderLineMapper;
import com.poojithairosha.order.mapper.OrderMapper;
import com.poojithairosha.order.repository.OrderLineRepository;
import com.poojithairosha.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = orderLineMapper.mapToEntity(request);
        return orderLineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::mapToResponse)
                .collect(Collectors.toList());
    }


}

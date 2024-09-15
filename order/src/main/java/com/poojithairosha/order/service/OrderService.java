package com.poojithairosha.order.service;

import com.poojithairosha.common.common.client.CustomerClient;
import com.poojithairosha.common.common.client.PaymentClient;
import com.poojithairosha.common.common.client.ProductClient;
import com.poojithairosha.common.customer.exception.CustomerNotFoundException;
import com.poojithairosha.common.notification.dto.OrderConfirmation;
import com.poojithairosha.common.order.dto.OrderLineRequest;
import com.poojithairosha.common.order.dto.OrderRequest;
import com.poojithairosha.common.order.dto.OrderResponse;
import com.poojithairosha.common.payment.dto.PaymentRequest;
import com.poojithairosha.common.product.dto.ProductPurchaseRequest;
import com.poojithairosha.common.product.dto.ProductPurchaseResponse;
import com.poojithairosha.order.kafka.OrderProducer;
import com.poojithairosha.order.mapper.OrderMapper;
import com.poojithairosha.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(@Valid OrderRequest request) {
        var customer = customerClient.findById(request.customerId()).orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id %s not found", request.customerId())));

        List<ProductPurchaseResponse> purchasedProducts = productClient.purchaseProducts(request.products()).orElseThrow(() -> new RuntimeException("Error purchasing products"));

        var order = this.orderRepository.save(orderMapper.mapToEntity(request));

        for (ProductPurchaseRequest pr : request.products()) {
            log.info("Product purchased: {}", pr.productId());
            orderLineService.saveOrderLine(
                    OrderLineRequest.builder()
                            .id(null)
                            .orderId(order.getId())
                            .productId(pr.productId())
                            .quantity(pr.quantity())
                            .build()
            );
        }

        // TODO: Start Payment Process
        var paymentRequest = PaymentRequest.builder()
                .amount(request.amount())
                .orderId(order.getId())
                .orderReference(request.reference())
                .paymentMethod(request.paymentMethod())
                .customer(customer)
                .build();
        paymentClient.createPayment(paymentRequest);

        // Send the order confirmation (Kafka)
        orderProducer.sendOrderConfirmation(
                OrderConfirmation.builder()
                        .orderReference(request.reference())
                        .totalAmount(request.amount())
                        .paymentMethod(request.paymentMethod())
                        .customerFirstname(customer.firstName())
                        .customerLastname(customer.lastName())
                        .customerEmail(customer.email())
                        .products(purchasedProducts)
                        .build()
        );

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return this.orderRepository.findAll()
                .stream()
                .map(this.orderMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return this.orderRepository.findById(id)
                .map(this.orderMapper::mapToResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }

}

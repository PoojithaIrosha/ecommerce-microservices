package com.poojithairosha.payment.service;

import com.poojithairosha.common.payment.dto.PaymentNotificationRequest;
import com.poojithairosha.common.payment.dto.PaymentRequest;
import com.poojithairosha.payment.kafka.PaymentNotificationProducer;
import com.poojithairosha.payment.mapper.PaymentMapper;
import com.poojithairosha.payment.repository.PaymentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentNotificationProducer notificationProducer;

    public Integer createPayment(@Valid PaymentRequest request) {
        var payment = this.paymentRepository.save(this.paymentMapper.mapToEntity(request));

        this.notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}

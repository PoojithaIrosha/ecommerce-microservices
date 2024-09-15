package com.poojithairosha.notification.kafka;

import com.poojithairosha.common.notification.dto.OrderConfirmation;
import com.poojithairosha.common.notification.dto.PaymentConfirmation;
import com.poojithairosha.common.notification.enums.NotificationType;
import com.poojithairosha.notification.entity.Notification;
import com.poojithairosha.notification.repository.NotificationRepository;
import com.poojithairosha.notification.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final MailService mailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info("Consumed payment confirmation: {}", paymentConfirmation);

        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();

        try {
            mailService.sendPaymentSuccessEmail(
                    paymentConfirmation.customerEmail(),
                    customerName,
                    paymentConfirmation.amount(),
                    paymentConfirmation.orderReference()
            );
        } catch (MessagingException e) {
            log.error("Error sending payment confirmation email: {}", e.getMessage());
        }
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {
        log.info("Consumed order confirmation: {}", orderConfirmation);

        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        var customerName = orderConfirmation.customerFirstname() + " " + orderConfirmation.customerLastname();

        try {
            mailService.sendOrderConfirmationEmail(
                    orderConfirmation.customerEmail(),
                    customerName,
                    orderConfirmation.totalAmount(),
                    orderConfirmation.orderReference(),
                    orderConfirmation.products()
            );
        } catch (MessagingException e) {
            log.error("Error sending order confirmation email: {}", e.getMessage());
        }
    }
}

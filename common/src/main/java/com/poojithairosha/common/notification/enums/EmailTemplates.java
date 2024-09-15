package com.poojithairosha.common.notification.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment Successfully Proceessed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order Confirmed");

    private final String template;
    private final String subject;
}

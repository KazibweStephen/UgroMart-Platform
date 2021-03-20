package com.ugromart.payments.services;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface PaymentsEventsProcessor {
    String ORDER_RECEIVED_PENDING_PAYMENT="order_out_for_payment";
    String ORDER_PAID="order_payment_successful";
    String ORDER_PAYMENT_DECLINED="order_payment_declined_rejected";

    @Input(ORDER_RECEIVED_PENDING_PAYMENT)
    SubscribableChannel receiveOrdersPendingPayment();

    @Output(ORDER_PAID)
    MessageChannel publishOrdersPaid();

    @Output(ORDER_PAYMENT_DECLINED)
    MessageChannel publishOrderPaymentDeclined();
}

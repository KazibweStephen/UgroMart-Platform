package com.ugromart.payments.services;

import com.ugromart.payments.models.OrderPaymentRequest;
import com.ugromart.payments.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class PaymentEventsService {
    private static final Logger log = LoggerFactory.getLogger(PaymentEventsService.class);

    @Autowired
    private PaymentsEventsProcessor paymentsEventsProcessor;
    @Autowired
    private PaymentsService paymentsService;
    @Autowired
    PaymentRepository paymentRepository;

    @StreamListener(PaymentsEventsProcessor.ORDER_RECEIVED_PENDING_PAYMENT)
    private void receiveOrderForPaymentProcessing(OrderPaymentRequest orderPaymentRequest){
        log.info("Order with id : {} received for customer Id: {} worth {} on ",orderPaymentRequest.getOrderId(),orderPaymentRequest.getCustomerId(), orderPaymentRequest.getTotalOrder().getAmount(), (new Date()).getTime());
        try{
            String paymentReferenceId= paymentsService.intiatePayment(orderPaymentRequest);
            orderPaymentRequest.setStatus("PAID");
            orderPaymentRequest.setPaymentReferenceId(paymentReferenceId);
            paymentRepository.save(orderPaymentRequest.convertToPayment());
            paymentsEventsProcessor.publishOrdersPaid().send(message(orderPaymentRequest));
        }catch (Exception ex){
            log.error("Payment for Order with id : {} received for customer Id: {} failed with error: "+ex.getMessage(),orderPaymentRequest.getOrderId(),orderPaymentRequest.getCustomerId(),ex);
            paymentsEventsProcessor.publishOrderPaymentDeclined().send(message(orderPaymentRequest));

        }

    }
    private static final <T> Message<T> message(T val){return MessageBuilder.withPayload(val).build();}

}


package com.ugromart.payments.repository;

import com.ugromart.payments.models.OrderPaymentRequest;
import com.ugromart.payments.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Payment save(Payment orderPaymentRequest);
 }

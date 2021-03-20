package com.ugromart.payments.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "payments")
public class Payment {
    @Id
    private UUID orderId;
    private long customerId;
    private BigDecimal totalOrder;
    private String customerPhoneNumber;
    private String Status;
    private UUID paymentReferenceId;
    private  String customerEmail;

    public Payment() {
    }


    public Payment(long customerId, BigDecimal totalOrder, String customerPhoneNumber, UUID orderId, String status) {
        this.customerId = customerId;
        this.totalOrder = totalOrder;
        this.customerPhoneNumber = customerPhoneNumber;
        this.orderId = orderId;
        Status = status;
    }

    public Payment(UUID orderId, long customerId, BigDecimal totalOrder, String customerPhoneNumber, String status, UUID paymentReferenceId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalOrder = totalOrder;
        this.customerPhoneNumber = customerPhoneNumber;
        Status = status;
        this.paymentReferenceId = paymentReferenceId;
    }

    public Payment(UUID orderId, long customerId, BigDecimal totalOrder, String customerPhoneNumber, String status, UUID paymentReferenceId, String customerEmail) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalOrder = totalOrder;
        this.customerPhoneNumber = customerPhoneNumber;
        Status = status;
        this.paymentReferenceId = paymentReferenceId;
        this.customerEmail = customerEmail;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(BigDecimal totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public UUID getPaymentReferenceId() {
        return paymentReferenceId;
    }

    public void setPaymentReferenceId(UUID paymentReferenceId) {
        this.paymentReferenceId = paymentReferenceId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment that = (Payment) o;
        return customerId == that.customerId &&
                totalOrder.equals(that.totalOrder) &&
                customerPhoneNumber.equals(that.customerPhoneNumber) &&
                orderId.equals(that.orderId) &&
                Objects.equals(Status, that.Status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, totalOrder, customerPhoneNumber, orderId, Status);
    }

    @Override
    public String toString() {
        return "OrderPaymentRequest{" +
                "customerId=" + customerId +
                ", totalOrder=" + totalOrder +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", orderId=" + orderId +
                ", Status='" + Status + '\'' +
                '}';
    }
}

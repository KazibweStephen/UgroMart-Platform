package com.ugromart.payments.models;

import java.util.Objects;
import java.util.UUID;

public class OrderPaymentRequest {
    private String orderId;
    private long customerId;
    private Money totalOrder;
    private String customerPhoneNumber;
    private String Status;
    private String paymentReferenceId;
    private String customerEmail;

    public OrderPaymentRequest() {
    }


    public OrderPaymentRequest(long customerId, Money totalOrder, String customerPhoneNumber, String orderId, String status) {
        this.customerId = customerId;
        this.totalOrder = totalOrder;
        this.customerPhoneNumber = customerPhoneNumber;
        this.orderId = orderId;
        Status = status;
    }

    public OrderPaymentRequest(String orderId, long customerId, Money totalOrder, String customerPhoneNumber, String status, String paymentReferenceId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalOrder = totalOrder;
        this.customerPhoneNumber = customerPhoneNumber;
        Status = status;
        this.paymentReferenceId = paymentReferenceId;
    }

    public OrderPaymentRequest(String orderId, long customerId, Money totalOrder, String customerPhoneNumber, String status, String paymentReferenceId, String customerEmail) {
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

    public Money getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Money totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPaymentReferenceId() {
        return paymentReferenceId;
    }

    public void setPaymentReferenceId(String paymentReferenceId) {
        this.paymentReferenceId = paymentReferenceId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }


    public Payment convertToPayment(){
        return new Payment(UUID.fromString(this.orderId),this.customerId,this.totalOrder.getAmount(),this.customerPhoneNumber,this.Status, UUID.fromString(this.paymentReferenceId),this.customerEmail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPaymentRequest that = (OrderPaymentRequest) o;
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

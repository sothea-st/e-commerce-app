package com.alibou.ecommerce.paymnet;

import com.alibou.ecommerce.customer.CustomerResponse;
import com.alibou.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
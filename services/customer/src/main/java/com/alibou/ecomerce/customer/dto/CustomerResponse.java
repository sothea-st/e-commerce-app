package com.alibou.ecomerce.customer.dto;

import com.alibou.ecomerce.customer.Address;


public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}

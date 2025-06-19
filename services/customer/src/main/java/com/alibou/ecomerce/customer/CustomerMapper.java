package com.alibou.ecomerce.customer;

import com.alibou.ecomerce.customer.dto.CustomerRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest customerRequest) {
        if(customerRequest == null) {
            return null;
        }
        return Customer.builder()
                .id(customerRequest.id())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .address(customerRequest.address())
                .email(customerRequest.email())
                .build();
    }
}

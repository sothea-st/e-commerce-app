package com.alibou.ecomerce.customer;

import com.alibou.ecomerce.customer.dto.CustomerRequest;
import com.alibou.ecomerce.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(@Valid CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.id())
                .orElseThrow(()-> new CustomerNotFoundException(
                        String.format("Cannot update customer :: No customer found with provided ID :: %s",customerRequest.id())
                ));
    }
}

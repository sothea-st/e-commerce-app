package com.alibou.ecomerce.customer;

import com.alibou.ecomerce.customer.dto.CustomerRequest;
import com.alibou.ecomerce.customer.dto.CustomerResponse;
import com.alibou.ecomerce.exception.CustomerNotFoundException;
import com.ctc.wstx.util.StringUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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

        mergerCustomer(customer,customerRequest);
        customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer, @Valid CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstName())) {
            customer.setFirstName(customerRequest.firstName());
        }
        if(StringUtils.isNotBlank(customerRequest.lastName())) {
            customer.setLastName(customerRequest.lastName());
        }
        if(StringUtils.isNotBlank(customerRequest.email())) {
            customer.setEmail(customerRequest.email());
        }
        if( customerRequest.email() != null) {
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

    public Boolean exitsById(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String id) {
        return this.customerRepository.findById(id)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
    }

    public void deleteCustomer(String id) {
        this.customerRepository.deleteById(id);
    }
}

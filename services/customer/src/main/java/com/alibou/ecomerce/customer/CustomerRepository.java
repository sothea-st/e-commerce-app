package com.alibou.ecomerce.customer;

import com.alibou.ecomerce.customer.dto.CustomerRequest;
import jakarta.validation.Valid;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {

}

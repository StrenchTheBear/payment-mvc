package com.usmp.service;

import com.usmp.dto.Customer;

import java.util.Map;

public interface CustomerService {

    Map<String, Object> customerRegistration(Customer customer);

}

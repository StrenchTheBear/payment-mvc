package com.usmp.service.Impl;

import com.usmp.dto.Customer;
import com.usmp.resttemplate.HttpClient;
import com.usmp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    private HttpClient httpClient;

    @Override
    public Map<String, Object> customerRegistration(Customer customer) {
        return this.httpClient.executeCreateCustomerService(customer);
    }

    @Autowired
    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}

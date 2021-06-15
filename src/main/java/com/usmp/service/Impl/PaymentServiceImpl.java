package com.usmp.service.Impl;

import com.usmp.dto.Payment;
import com.usmp.resttemplate.HttpClient;
import com.usmp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    private HttpClient httpClient;

    @Override
    public Map<String, Object> doPayment(Payment payment) {
        return this.httpClient.executePaymentService(payment);
    }

    @Autowired
    public void setHttpClient(HttpClient httpClient) { this.httpClient = httpClient; }

}

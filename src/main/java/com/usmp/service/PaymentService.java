package com.usmp.service;

import com.usmp.dto.Payment;

import java.util.Map;

public interface PaymentService {

    Map<String, Object> doPayment(Payment payment);

}

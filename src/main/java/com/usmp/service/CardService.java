package com.usmp.service;

import com.usmp.dto.RegisterCard;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CardService {

    Map<String, Object> getCustomerCards(Integer customerId);
    ResponseEntity<Map> deleteCustomerCard(String cardNumber);
    Map<String, Object> insertCustomerCard(RegisterCard registerCard);

}

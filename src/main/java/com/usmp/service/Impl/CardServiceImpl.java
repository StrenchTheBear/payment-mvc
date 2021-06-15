package com.usmp.service.Impl;

import com.usmp.dto.RegisterCard;
import com.usmp.resttemplate.HttpClient;
import com.usmp.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CardServiceImpl implements CardService {

    private HttpClient httpClient;

    @Override
    public Map<String, Object> getCustomerCards(Integer customerId) {
        return this.httpClient.executeGetCustomerCards(customerId);
    }

    @Override
    public ResponseEntity<Map> deleteCustomerCard(String cardNumber) {
        return this.httpClient.executeDeleteCustomerCard(cardNumber);
    }

    @Override
    public Map<String, Object> insertCustomerCard(RegisterCard registerCard) {
        return this.httpClient.executeInsertCustomerCard(registerCard);
    }

    @Autowired
    public void setHttpClient(HttpClient httpClient) { this.httpClient = httpClient; }

}

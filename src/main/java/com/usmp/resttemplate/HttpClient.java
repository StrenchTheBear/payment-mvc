package com.usmp.resttemplate;

import com.usmp.dto.Customer;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class HttpClient {

    private RestTemplate restTemplate;

    private static final String CREATE_CUSTOMER_ENDPOINT = "https://usmppayment-api.herokuapp.com/payment-business/customers/create";
    private static final String GET_CUSTOMER_CARDS_ENDPOINT = "https://usmppayment-api.herokuapp.com/payment-business/customers/cards/";
    private static final String DELETE_CUSTOMER_CARD_ENDPOINT = "https://usmppayment-api.herokuapp.com/payment-business/cards/";

    public HttpClient() {
        this.restTemplate = new RestTemplate();
    }

    public Map<String, Object> executeCreateCustomerService(Customer customer) {
        Map<String, Object> response = null;
        HttpEntity<Customer> entity = new HttpEntity<>(customer, createHttpHeaders());
        try {
            response = this.restTemplate.postForObject(CREATE_CUSTOMER_ENDPOINT, entity, Map.class);
            System.out.println("Respuesta: " + response);
        } catch (RestClientException ex) {
            System.out.println("Ocurrió un problema al consumir el servicio: " + ex.getMostSpecificCause());
            response = new HashMap<>();
            response.put("errors", "Por favor ingrese los datos correctamente");
        }
        return response;
    }

    public Map<String, Object> executeGetCustomerCards(Integer customerId) {
        Map<String, Object> response = null;
        String uri = GET_CUSTOMER_CARDS_ENDPOINT.concat(customerId.toString());
        try {
            response = this.restTemplate.getForObject(uri, Map.class);
        } catch (RestClientException ex) {
            System.out.println("Ocurrió un problema al consumir el servicio: " + ex.getMostSpecificCause().getMessage());
        }
        return response;
    }

    public ResponseEntity<Map> executeDeleteCustomerCard(String cardNumber) {
        ResponseEntity<Map> response = null;
        String uri = DELETE_CUSTOMER_CARD_ENDPOINT.concat(cardNumber);
        HttpEntity<Void> entity = new HttpEntity<>(createHttpHeaders());
        try {
            response = this.restTemplate.exchange(uri, HttpMethod.DELETE, entity, Map.class);
        } catch (RestClientException ex) {
            System.out.println("Ocurrió un problema al consumir el servicio: " + ex.getMostSpecificCause().getMessage());
        }
        return response;
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}

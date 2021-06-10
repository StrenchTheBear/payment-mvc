package com.usmp.resttemplate;

import com.usmp.dto.Customer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class HttpClient {

    private RestTemplate restTemplate;

    private static final String CREATE_CUSTOMER_ENDPOINT = "https://usmppayment-api.herokuapp.com/payment-business/customers/create";

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

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}

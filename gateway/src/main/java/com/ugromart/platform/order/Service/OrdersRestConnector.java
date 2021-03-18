package com.ugromart.platform.order.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrdersRestConnector {
    @Value("${service.ordersms.url}")
    private String url;
    private final RestTemplate restTemplate;

    public OrdersRestConnector(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public <T> T get(Class<T> responseType, String endPoint){
        T response = restTemplate.exchange(url+endPoint, HttpMethod.GET,null,responseType).getBody();
        return response;
    }
}

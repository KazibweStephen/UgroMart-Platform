package com.ugromart.platform.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ProductsRestConnector {
    private static final Logger log = LoggerFactory.getLogger(ProductsRestConnector.class);

    @Value("${service.productsms.url}")
    private String url;
    private final RestTemplate restTemplate;


    public ProductsRestConnector(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public <T> T get(Class<T> responseType, String endPoint){
        log.info(url+endPoint);
        //return restTemplate.getForObject(url+endPoint,responseType);
        T result=restTemplate.exchange(url+endPoint, HttpMethod.GET,null,responseType).getBody();
        return  result;
    }

}

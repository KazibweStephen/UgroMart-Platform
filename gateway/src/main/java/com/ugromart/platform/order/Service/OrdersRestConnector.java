package com.ugromart.platform.order.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrdersRestConnector {
    @Value("service.ordersms.url")
    private String url;
    private RestTemplate restTemplate;


    public OrdersRestConnector(){
        this.restTemplate=new RestTemplate();
//        url="http://"+System.getenv("ORDERS-MS-URL")+":8082/ordersms";
    }

    public <T> T get(Class<T> responseType, String endPoint){

        return restTemplate.getForObject(url+endPoint,responseType);
    }
}

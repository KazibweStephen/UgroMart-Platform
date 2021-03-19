package com.ugromart.payments.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Service
public class PaymentsService {
    private final Logger log= LoggerFactory.getLogger(PaymentsService.class);
    @Autowired
    RestTemplate restTemplate;
    @Value("${payments.mtn-momo.base-url}")
    private String baseUrl;
    @Value("${payments.mtn-momo.primarykey}")
    private String subscriptionKey;

    public void intiatePayment(){
        HttpHeaders headers =new HttpHeaders();
        headers.set("Authorization","");
        headers.set("X-Target-Environment", "");
        headers.set("X-Callback-Url", "");
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        headers.set("Ocp-Apim-Subscription-Key", subscriptionKey);
        MultiValueMap<String, String> bodyMap= new LinkedMultiValueMap<>();

        bodyMap.add("{body}","{body}");
        HttpEntity<MultiValueMap<String,String>> request= new HttpEntity<>(bodyMap,headers);

        ResponseEntity<String> response=restTemplate.exchange(baseUrl+"/bc-authorize", HttpMethod.POST,request,String.class);
        Assert.isTrue(response.getStatusCode().is2xxSuccessful());

        //login_hint=ID:{msisdn}/MSISDN&scope={scope}&access_type={online/offline}
    }

    public void createApiUser(){
        HttpHeaders headers =new HttpHeaders();
        String uuid=UUID.randomUUID().toString();
        log.info(uuid);
        headers.set("X-Reference-Id", uuid);
        headers.set("Ocp-Apim-Subscription-Key", subscriptionKey);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        MultiValueMap<String, String> bodyMap= new LinkedMultiValueMap<>();

        bodyMap.add("providerCallbackHost", "http://192.168.0.104:8084");
        HttpEntity<MultiValueMap<String,String>> request= new HttpEntity<>(bodyMap,headers);

        ResponseEntity<String> response=restTemplate.exchange("https://sandbox.momodeveloper.mtn.com/collection/v1_0"+"/apiuser", HttpMethod.POST,request,String.class);
        Assert.isTrue(response.getStatusCode().is2xxSuccessful());
        log.info("ApI User created with ststus: "+ response.getStatusCode().toString());

        //login_hint=ID:{msisdn}/MSISDN&scope={scope}&access_type={online/offline}
    }

}

package com.ugromart.payments.services;

import com.ugromart.payments.models.GetTokenResponse;
import com.ugromart.payments.models.MomoPayement;
import com.ugromart.payments.models.OrderPaymentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
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
    @Value("${apiKey}")
    private String apiKey;
    @Value("${X-Reference-Id}")
    private String XReferenceId;
    @Value("${access_token}")
    private String access_token;

    private  HttpHeaders headers =new HttpHeaders();

    public PaymentsService(){
    }

    public String intiatePayment(OrderPaymentRequest paymentRequest) throws Exception {
        access_token=getToken();
        addCommonHeaders();
        String paymentReferenceId=UUID.randomUUID().toString();
        log.info("Payment ReferenceId: "+paymentReferenceId);//Payment Reference Id, for querying later
        headers.set("X-Reference-Id", paymentReferenceId);
        headers.set("X-Target-Environment", "sandbox");
        headers.set("Authorization","Bearer "+access_token);
        MomoPayement payment= new MomoPayement(
                paymentRequest.getTotalOrder().getAmount().doubleValue(),
                "EUR", "UGROMAT_ORDERS",
                "Test payment for orders",
                "Test payment for orders",
                "msisdn",
                paymentRequest.getCustomerPhoneNumber());

        HttpEntity<Object> request= new HttpEntity<>(payment,headers);

            ResponseEntity<String> response=restTemplate.exchange(baseUrl+"/collection/v1_0/requesttopay", HttpMethod.POST,request,String.class);
            if(!response.getStatusCode().is2xxSuccessful()){
                throw new Exception("Payment processing failed");
            };
            return paymentReferenceId;
    }

    public String getToken(){
        String authStr= Base64.getEncoder().encodeToString((XReferenceId+":"+apiKey).getBytes());
        addCommonHeaders();
        headers.set("Authorization","Basic "+authStr);
        HttpEntity<Object> request=new HttpEntity<>("{body}",headers);
        ResponseEntity<GetTokenResponse> responseEntity = restTemplate.exchange(baseUrl+"/collection/token/",HttpMethod.POST,request,GetTokenResponse.class);
        GetTokenResponse response =responseEntity.getBody();
        String token= response.getAccess_token();
        log.info("Access Token: "+token);
        return token;
    }

    private void addCommonHeaders() {
        headers.set("Ocp-Apim-Subscription-Key", subscriptionKey);
        headers.set("Content-Type", "application/json");
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

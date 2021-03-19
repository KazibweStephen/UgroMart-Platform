package com.ugromart.payments.api;


import com.ugromart.payments.services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payments")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;
    @GetMapping("/requestForPayment")
    public void requestForPayment(){
        paymentsService.createApiUser();
    }
}

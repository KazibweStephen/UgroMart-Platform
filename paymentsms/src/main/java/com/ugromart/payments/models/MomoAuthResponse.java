package com.ugromart.payments.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MomoAuthResponse {
    private String auth_req_id;
    private Double interval;
    private Double expires_in;
}

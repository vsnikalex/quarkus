package com.telekom.saga.order.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;

import javax.enterprise.inject.Model;

@Getter
@RegisterForReflection
@Model
public class PaymentDTO {

    private String cardNumber;
    private Integer cost;
}

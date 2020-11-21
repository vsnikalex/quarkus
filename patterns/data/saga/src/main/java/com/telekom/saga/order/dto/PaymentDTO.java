package com.telekom.saga.order.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;

@Getter
@RegisterForReflection
public class PaymentDTO {

    private String cardNumber;
    private Integer cost;
}

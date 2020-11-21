package com.telekom.saga.order.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;

@Getter
@RegisterForReflection
public class OrderDTO {

    private String date;

    private CustomerDTO customer;
    private FoodDTO food;
    private PaymentDTO payment;
}

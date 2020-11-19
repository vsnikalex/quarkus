package com.telekom.saga.order.dto;

import lombok.Getter;

@Getter
public class OrderDTO {

    private String date;

    private CustomerDTO customer;
    private FoodDTO food;
    private PaymentDTO payment;
}

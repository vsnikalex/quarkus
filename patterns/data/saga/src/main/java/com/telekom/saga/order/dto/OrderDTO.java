package com.telekom.saga.order.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;

import javax.enterprise.inject.Model;
import java.util.UUID;

@Getter
@RegisterForReflection
@Model
public class OrderDTO {

    String id;
    String date;

    CustomerDTO customer;
    FoodDTO food;
    PaymentDTO payment;

    public void generateId() {
        this.id = UUID.randomUUID().toString();
        customer.orderId = this.id;
        food.orderId = this.id;
        payment.orderId = this.id;
    }
}

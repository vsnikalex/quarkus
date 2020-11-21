package com.telekom.saga.order.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;

import javax.enterprise.inject.Model;

@Getter
@RegisterForReflection
@Model
public class CustomerDTO {

    private String name;
    private String surname;
    private String phoneNumber;
}

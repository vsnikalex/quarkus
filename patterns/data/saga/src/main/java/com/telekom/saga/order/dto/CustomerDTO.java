package com.telekom.saga.order.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;

@Getter
@RegisterForReflection
public class CustomerDTO {

    private String name;
    private String surname;
    private String phoneNumber;
}

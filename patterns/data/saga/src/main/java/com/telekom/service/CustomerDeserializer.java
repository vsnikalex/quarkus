package com.telekom.service;

import com.telekom.saga.order.dto.CustomerDTO;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class CustomerDeserializer extends ObjectMapperDeserializer<CustomerDTO> {

    public CustomerDeserializer() {
        super(CustomerDTO.class);
    }
}

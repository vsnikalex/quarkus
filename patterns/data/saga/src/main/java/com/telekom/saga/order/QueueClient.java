package com.telekom.saga.order;

import com.telekom.saga.order.dto.CustomerDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;

public class QueueClient {

    @Inject
    @Channel("customer-create")
    Emitter<CustomerDTO> customerEmitter;

    public void sendMessage() {
        customerEmitter.send(new CustomerDTO());
    }
}

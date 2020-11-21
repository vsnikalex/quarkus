package com.telekom.saga.order;

import com.telekom.saga.order.dto.CustomerDTO;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;

public class QueueClient {

    Emitter<CustomerDTO> customerEmitter;

    @Inject
    public QueueClient(Emitter<CustomerDTO> customerEmitter) {
        this.customerEmitter = customerEmitter;
    }

    public void sendMessage() {
        customerEmitter.send(new CustomerDTO());
    }
}

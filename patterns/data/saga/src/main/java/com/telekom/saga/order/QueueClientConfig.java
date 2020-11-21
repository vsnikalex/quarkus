package com.telekom.saga.order;

import com.telekom.saga.order.dto.CustomerDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Produces;

@Dependent
public class QueueClientConfig {

    @Inject
    @Channel("customer-create")
    Emitter<CustomerDTO> customerEmitter;

    @Produces
    @ApplicationScoped
    public QueueClient queueClient() {
        return new QueueClient(customerEmitter);
    }
}

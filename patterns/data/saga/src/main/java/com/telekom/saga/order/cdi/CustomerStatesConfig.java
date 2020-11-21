package com.telekom.saga.order.cdi;

import com.telekom.saga.order.CreateOrderSaga;
import com.telekom.saga.order.dto.CustomerDTO;
import com.telekom.saga.order.states.CustomerVerifying;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Produces;

@Dependent
public class CustomerStatesConfig {

    @Inject
    @Channel("customer-create")
    Emitter<CustomerDTO> customerEmitter;

    @Produces
    @ApplicationScoped
    public CustomerVerifying customerVerifying(CreateOrderSaga saga) {
        return new CustomerVerifying(saga, customerEmitter);
    }
}

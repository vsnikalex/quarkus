package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;
import com.telekom.saga.order.dto.CustomerDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.inject.Inject;

public class CustomerVerifying extends CreateOrderSagaState implements Compensatable {

    private static final Logger LOGGER = Logger.getLogger(CustomerVerifying.class);

    @Inject
    @Channel("customer-create")
    Emitter<CustomerDTO> customerEmitter;

    public CustomerVerifying(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {
        LOGGER.info("Verifying Customer");

        CustomerDTO customer = saga.getOrder().getCustomer();

        customerEmitter.send(customer).whenComplete((success, failure) -> {
            if (failure != null) {
                LOGGER.errorf("Customer Messaging Failed: %s", failure.getMessage());
            } else {
                LOGGER.info("Customer Acknowledged");
            }
        });
    }

    @Override
    public void onSuccess() {
        LOGGER.info("Consumer Verified");
        saga.setState(new TicketCreating(saga));
    }

    @Override
    public void onFail() {

    }
}

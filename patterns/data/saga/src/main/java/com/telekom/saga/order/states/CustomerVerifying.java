package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;
import com.telekom.saga.order.dto.CustomerDTO;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.inject.Inject;

public class CustomerVerifying extends CreateOrderSagaState implements Compensatable {

    private static final Logger LOGGER = Logger.getLogger(CustomerVerifying.class);

    Emitter<CustomerDTO> customerEmitter;

    @Inject
    public CustomerVerifying(CreateOrderSaga saga, Emitter<CustomerDTO> customerEmitter) {
        super(saga);
        this.customerEmitter = customerEmitter;
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
        LOGGER.info("Customer Verified");

        // TODO: set state to TicketCreating
    }

    @Override
    public void onFail() {
        LOGGER.info("Customer Data is not Valid");

        // TODO: set state to OrderRejecting
    }
}

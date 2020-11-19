package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;
import com.telekom.saga.order.dto.CustomerDTO;
import org.jboss.logging.Logger;

public class CustomerVerifying extends CreateOrderSagaState implements Compensatable {

    private static final Logger LOGGER = Logger.getLogger(CustomerVerifying.class);

    public CustomerVerifying(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {
        LOGGER.info("Verifying Consumer");
        // TODO: implement messaging to customer service
        CustomerDTO customer = saga.getOrder().getCustomer();
    }

    @Override
    public void onSuccess() {
        LOGGER.info("Consumer Verified");
        saga.setState(new TicketCreating(saga));
        saga.getState().onAction();
    }

    @Override
    public void onFail() {

    }
}

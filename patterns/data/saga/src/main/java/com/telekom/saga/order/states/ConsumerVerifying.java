package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;
import org.jboss.logging.Logger;

public class ConsumerVerifying extends CreateOrderSagaState implements Compensatable {

    private static final Logger LOGGER = Logger.getLogger(ConsumerVerifying.class);

    public ConsumerVerifying(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {
        LOGGER.info("Verifying Consumer");
        LOGGER.info("Consumer Verified");
        saga.setState(new TicketCreating(saga));
        saga.getState().onAction();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }
}

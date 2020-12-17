package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;
import org.jboss.logging.Logger;

public class TicketCreating extends CreateOrderSagaState implements Compensatable {

    private static final Logger LOGGER = Logger.getLogger(TicketCreating.class);

    public TicketCreating(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {
        LOGGER.info("Creating Ticket");
        LOGGER.info("Ticket Created");
        saga.setState(new CardAuthorizing(saga));
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }
}

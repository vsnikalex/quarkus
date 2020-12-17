package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;
import org.jboss.logging.Logger;

public class CardAuthorizing extends CreateOrderSagaState implements Compensatable {

    private static final Logger LOGGER = Logger.getLogger(CardAuthorizing.class);

    public CardAuthorizing(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {
        LOGGER.info("Authorizing Card");
        LOGGER.info("Card authorized");
        saga.setState(new TicketApproving(saga));
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }
}

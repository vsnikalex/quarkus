package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;
import org.jboss.logging.Logger;

public class TicketApproving extends CreateOrderSagaState {

    private static final Logger LOGGER = Logger.getLogger(TicketApproving.class);

    public TicketApproving(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {
        LOGGER.info("Approving Ticket");
        LOGGER.info("Ticket Approved");
        saga.setState(new OrderApproved(saga));
        saga.getState().onAction();
    }
}

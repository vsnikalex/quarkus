package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;
import org.jboss.logging.Logger;

public class OrderApproved extends CreateOrderSagaState {

    private static final Logger LOGGER = Logger.getLogger(OrderApproved.class);

    public OrderApproved(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {
        LOGGER.info("Order Approved");
    }
}

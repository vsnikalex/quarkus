package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;

public class TicketRejecting extends CreateOrderSagaState {

    public TicketRejecting(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }
}

package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;

public class TicketApproving extends CreateOrderSagaState {

    public TicketApproving(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }
}

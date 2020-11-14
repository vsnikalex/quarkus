package com.telekom.saga.order.states;

public class TicketRejecting extends CreateOrderSagaState {

    public TicketRejecting(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }
}

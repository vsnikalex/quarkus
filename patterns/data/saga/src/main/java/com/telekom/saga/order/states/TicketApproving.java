package com.telekom.saga.order.states;

public class TicketApproving extends CreateOrderSagaState {

    public TicketApproving(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }
}

package com.telekom.saga.order.states;

public class TicketCreating extends CreateOrderSagaState implements Compensatable {

    public TicketCreating(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }
}

package com.telekom.saga.order.states;

public class OrderApproving extends CreateOrderSagaState {

    public OrderApproving(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }
}

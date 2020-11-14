package com.telekom.saga.order.states;

public class OrderRejecting extends CreateOrderSagaState {

    public OrderRejecting(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }
}

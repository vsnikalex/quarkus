package com.telekom.saga.order.states;

public class OrderRejected extends CreateOrderSagaState {

    public OrderRejected(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }
}

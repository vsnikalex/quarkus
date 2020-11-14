package com.telekom.saga.order.states;

public class OrderApproved extends CreateOrderSagaState {

    public OrderApproved(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }
}

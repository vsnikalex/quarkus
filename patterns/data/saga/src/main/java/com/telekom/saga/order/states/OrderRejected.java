package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;

public class OrderRejected extends CreateOrderSagaState {

    public OrderRejected(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }
}

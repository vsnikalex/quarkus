package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;

public class OrderRejecting extends CreateOrderSagaState {

    public OrderRejecting(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }
}

package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;

public class OrderApproving extends CreateOrderSagaState {

    public OrderApproving(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }
}

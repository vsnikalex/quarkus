package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;

public class OrderApproved extends CreateOrderSagaState {

    public OrderApproved(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }
}

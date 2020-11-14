package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;

public abstract class CreateOrderSagaState {

    CreateOrderSaga saga;

    CreateOrderSagaState(CreateOrderSaga saga) {
        this.saga = saga;
    }

    public abstract void onAction();
}

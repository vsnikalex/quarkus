package com.telekom.saga.order.states;

public abstract class CreateOrderSagaState {

    CreateOrderSaga saga;

    CreateOrderSagaState(CreateOrderSaga saga) {
        this.saga = saga;
    }

    public abstract void onAction();
}

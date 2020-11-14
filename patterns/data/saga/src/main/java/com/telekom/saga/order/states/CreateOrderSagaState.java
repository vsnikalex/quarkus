package com.telekom.saga.order.states;

public abstract class CreateOrderSagaState {

    CreateOrderSaga saga;

    public CreateOrderSagaState(CreateOrderSaga saga) {
        this.saga = saga;
    }

    public abstract void onAction();
}

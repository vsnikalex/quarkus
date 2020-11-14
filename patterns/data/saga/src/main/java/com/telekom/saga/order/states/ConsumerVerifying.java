package com.telekom.saga.order.states;

public class ConsumerVerifying extends CreateOrderSagaState implements Compensatable {

    public ConsumerVerifying(CreateOrderSaga saga) {
        super(saga);
    }

    @Override
    public void onAction() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }
}

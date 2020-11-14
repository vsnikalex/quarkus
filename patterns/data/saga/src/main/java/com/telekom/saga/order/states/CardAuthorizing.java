package com.telekom.saga.order.states;

public class CardAuthorizing extends CreateOrderSagaState implements Compensatable {

    public CardAuthorizing(CreateOrderSaga saga) {
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

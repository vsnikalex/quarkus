package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;

public abstract class CreateOrderSagaState {

    CreateOrderSaga saga;

    public CreateOrderSagaState() {
        // added due to test DeploymentException:
        // It's not possible to add a synthetic constructor with no parameters to
        // the unproxyable return type of PRODUCER METHOD bean
    }

    CreateOrderSagaState(CreateOrderSaga saga) {
        this.saga = saga;
    }

    public abstract void onAction();
}

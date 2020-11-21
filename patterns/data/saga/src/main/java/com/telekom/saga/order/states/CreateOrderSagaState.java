package com.telekom.saga.order.states;

import com.telekom.saga.order.CreateOrderSaga;
import lombok.NoArgsConstructor;

/**
 * No args constructor added due to test DeploymentException:
 *  It's not possible to add a synthetic constructor with no parameters to
 *  the unproxyable return type of PRODUCER METHOD bean...
 */
@NoArgsConstructor
public abstract class CreateOrderSagaState {

    CreateOrderSaga saga;

    CreateOrderSagaState(CreateOrderSaga saga) {
        this.saga = saga;
    }

    public abstract void onAction();
}

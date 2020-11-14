package com.telekom.saga.order;

import com.telekom.saga.order.states.Compensatable;
import com.telekom.saga.order.states.ConsumerVerifying;
import com.telekom.saga.order.states.CreateOrderSagaState;

public class CreateOrderSaga {

    private CreateOrderSagaState state;

    public CreateOrderSaga() {
        this.state = new ConsumerVerifying(this);
        this.state.onAction();
    }

    public void changeState(CreateOrderSagaState state) {
        this.state = state;
    }

    // TODO: connect to create order saga reply channel
    private void onMessage(boolean success) {
        if (!(state instanceof Compensatable)) {
            return;
        }

        if (success) {
            ((Compensatable) state).onSuccess();
        } else {
            ((Compensatable) state).onFail();
        }
    }
}

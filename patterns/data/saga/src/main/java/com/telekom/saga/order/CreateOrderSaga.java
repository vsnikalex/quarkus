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

    // TODO: connect to create order saga reply channel
    private void onMessage(boolean success) {
        if (state instanceof Compensatable) {
            Compensatable compensatableState = (Compensatable) state;

            if (success) {
                compensatableState.onSuccess();
            } else {
                compensatableState.onFail();
            }
        }
    }

    public CreateOrderSagaState getState() {
        return state;
    }

    public void setState(CreateOrderSagaState state) {
        this.state = state;
    }
}

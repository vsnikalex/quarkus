package com.telekom.saga.order;

import com.telekom.saga.order.dto.OrderDTO;
import com.telekom.saga.order.states.Compensatable;
import com.telekom.saga.order.states.CreateOrderSagaState;
import com.telekom.saga.order.states.CustomerVerifying;
import lombok.Getter;

@Getter
public class CreateOrderSaga {

    private CreateOrderSagaState state;
    private final OrderDTO order;

    public CreateOrderSaga(OrderDTO order) {
        this.order = order;
        setState(new CustomerVerifying(this));
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

    public void setState(CreateOrderSagaState state) {
        this.state = state;
        this.state.onAction();
    }
}

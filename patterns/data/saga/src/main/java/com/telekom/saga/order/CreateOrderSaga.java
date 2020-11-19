package com.telekom.saga.order;

import com.telekom.saga.order.dto.OrderDTO;
import com.telekom.saga.order.states.Compensatable;
import com.telekom.saga.order.states.CreateOrderSagaState;
import com.telekom.saga.order.states.CustomerVerifying;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateOrderSaga {

    @Setter
    private CreateOrderSagaState state;
    private final OrderDTO order;

    public CreateOrderSaga(OrderDTO order) {
        this.order = order;

        this.state = new CustomerVerifying(this);
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
}

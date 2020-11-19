package com.telekom.saga.order;

import com.telekom.saga.order.dto.OrderDTO;
import com.telekom.saga.order.states.Compensatable;
import com.telekom.saga.order.states.CreateOrderSagaState;
import com.telekom.saga.order.states.CustomerVerifying;

public class CreateOrderSaga {

    private CreateOrderSagaState state;
    private final OrderDTO orderDTO;

    public CreateOrderSaga(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;

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

    public CreateOrderSagaState getState() {
        return state;
    }

    public void setState(CreateOrderSagaState state) {
        this.state = state;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }
}

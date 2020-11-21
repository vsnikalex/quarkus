package com.telekom.saga.order;

import com.telekom.saga.order.cdi.CustomerStatesConfig;
import com.telekom.saga.order.dto.OrderDTO;
import com.telekom.saga.order.states.Compensatable;
import com.telekom.saga.order.states.CreateOrderSagaState;
import lombok.Getter;

import javax.inject.Inject;

@Getter
public class CreateOrderSaga {

    CreateOrderSagaState state;
    OrderDTO order;

    CustomerStatesConfig customerStatesConfig;

    @Inject
    public CreateOrderSaga(OrderDTO order, CustomerStatesConfig customerStatesConfig) {
        this.order = order;
        setState(customerStatesConfig.customerVerifying(this));
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

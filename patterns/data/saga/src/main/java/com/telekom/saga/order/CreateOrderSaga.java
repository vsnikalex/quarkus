package com.telekom.saga.order;

import com.telekom.saga.order.cdi.CustomerStatesConfig;
import com.telekom.saga.order.dto.OrderDTO;
import com.telekom.saga.order.dto.StateUpdate;
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

    void onMessage(StateUpdate stateUpdate) {
        if (state instanceof Compensatable) {
            Compensatable compensatableState = (Compensatable) state;

            if (stateUpdate.isSuccess()) {
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

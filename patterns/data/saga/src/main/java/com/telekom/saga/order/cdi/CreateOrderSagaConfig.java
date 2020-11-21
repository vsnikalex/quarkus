package com.telekom.saga.order.cdi;

import com.telekom.saga.order.CreateOrderSaga;
import com.telekom.saga.order.dto.OrderDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Produces;

@Dependent
public class CreateOrderSagaConfig {

    @Inject
    CustomerStatesConfig customerStatesConfig;

    @Produces
    @ApplicationScoped
    public CreateOrderSaga createOrderSaga(OrderDTO orderDTO) {
        return new CreateOrderSaga(orderDTO, customerStatesConfig);
    }
}

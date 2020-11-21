package com.telekom.saga.order;

import com.telekom.saga.order.cdi.CreateOrderSagaConfig;
import com.telekom.saga.order.dto.OrderDTO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/createOrder")
public class OrderResource {

    List<CreateOrderSaga> createOrderSagas = new ArrayList<>();

    @Inject
    CreateOrderSagaConfig createOrderSagaConfig;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createOrder(OrderDTO orderDTO) {
        createOrderSagas.add(createOrderSagaConfig.createOrderSaga(orderDTO));

        return Response.accepted("Order Accepted").build();
    }
}

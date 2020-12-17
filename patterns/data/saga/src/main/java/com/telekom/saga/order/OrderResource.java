package com.telekom.saga.order;

import com.telekom.saga.order.cdi.CreateOrderSagaConfig;
import com.telekom.saga.order.dto.OrderDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/createOrder")
public class OrderResource {

    static Map<String, CreateOrderSaga> createOrderSagas = new HashMap<>();

    @Inject
    CreateOrderSagaConfig createOrderSagaConfig;

    @Inject
    @Channel("new-orders-stream")
    Emitter<OrderDTO> newOrdersEmitter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createOrder(OrderDTO orderDTO) {
        orderDTO.generateId();

        // Create saga entry in "database"
        createOrderSagas.put(orderDTO.getId(), createOrderSagaConfig.createOrderSaga(orderDTO));

        // Notify frontend that a new order created
        newOrdersEmitter.send(orderDTO);

        return Response.accepted("Order Accepted").build();
    }
}

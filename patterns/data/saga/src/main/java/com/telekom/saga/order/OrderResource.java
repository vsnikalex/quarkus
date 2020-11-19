package com.telekom.saga.order;

import com.telekom.saga.order.dto.OrderDTO;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/createOrder")
public class OrderResource {

    List<CreateOrderSaga> createOrderSagas;

    @PostConstruct
    void init() {
        createOrderSagas = new ArrayList<>();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response createOrder(OrderDTO orderDTO) {
        createOrderSagas.add(new CreateOrderSaga(orderDTO));

        return Response.accepted("Order Accepted").build();
    }
}

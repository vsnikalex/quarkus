package com.telekom.saga.order;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/createOrder")
public class OrderResource {

    List<CreateOrderSaga> createOrderSagas;

    @PostConstruct
    void init() {
        createOrderSagas = new ArrayList<>();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response createOrder() {
        createOrderSagas.add(new CreateOrderSaga());

        return Response.accepted("Order Accepted").build();
    }
}

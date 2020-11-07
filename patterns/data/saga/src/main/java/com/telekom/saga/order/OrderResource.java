package com.telekom.saga.order;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/createOrder")
public class OrderResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String createOrder() {
        return "order created";
    }
}
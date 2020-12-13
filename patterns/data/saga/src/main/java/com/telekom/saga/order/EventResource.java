package com.telekom.saga.order;

import com.telekom.saga.order.dto.OrderDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/events")
public class EventResource {

    @Inject
    @Channel("new-orders-stream")
    Publisher<OrderDTO> orders;

    @GET
    @Path("/newOrders")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Publisher<OrderDTO> stream() {
        return orders;
    }
}

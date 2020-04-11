package com.tsystems.kafka;

import io.smallrye.reactive.messaging.annotations.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * A simple resource retrieving the in-memory "my-data-stream" and sending the items as server-sent events.
 */
@Path("/prices")
public class PriceResource {

    @Inject
    @Channel("my-data-stream")
    Publisher<Double> prices;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    public Publisher<Double> stream() {
        return prices;
    }

    @Inject
    @Channel("price-create")
    Emitter<Double> priceEmitter;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void addPrice(Double price) {
        priceEmitter.send(price);
    }
}

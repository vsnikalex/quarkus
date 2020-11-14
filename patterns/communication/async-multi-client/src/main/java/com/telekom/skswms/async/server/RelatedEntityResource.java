package com.telekom.skswms.async.server;

import io.smallrye.mutiny.Uni;
import java.time.Duration;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/relatedEntity")
public class RelatedEntityResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<Response> getRelatedEntity() {
        // Mimic an asynchronous computation.
        return Uni.createFrom()
                .item(() -> Response.ok("Related Entity").build())
                .onItem().delayIt().by(Duration.ofSeconds(3));
    }
}

package com.tsystems.dynamodb;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/async-fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitAsyncResource {

    @Inject
    FruitAsyncService service;

    @GET
    public Uni<List<Fruit>> getAll() {
        return service.findAll();
    }

    @GET
    @Path("{name}")
    public Uni<Fruit> getSingle(@PathParam("name") String name) {
        return service.get(name);
    }

    @POST
    public Uni<List<Fruit>> add(Fruit fruit) {
        return service.add(fruit)
                .onItem().ignore().andSwitchTo(this::getAll);
    }
}

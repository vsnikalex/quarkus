package com.tsystems.vertx;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    @Inject
    io.vertx.mutiny.pgclient.PgPool client;

    @Inject
    @ConfigProperty(name = "myapp.schema.create", defaultValue = "true")
    boolean schemaCreate;

    @PostConstruct
    void config() {
        if (schemaCreate) {
            initdb();
        }
    }

    private void initdb() {
        client.query("DROP TABLE IF EXISTS fruits")
                .flatMap(r -> client.query("CREATE TABLE fruits (id SERIAL PRIMARY KEY, name TEXT NOT NULL)"))
                .flatMap(r -> client.query("INSERT INTO fruits (name) VALUES ('Orange')"))
                .flatMap(r -> client.query("INSERT INTO fruits (name) VALUES ('Pear')"))
                .flatMap(r -> client.query("INSERT INTO fruits (name) VALUES ('Apple')"))
                .await().indefinitely();
    }

    @GET
    public Uni<Response> get() {
        return Fruit.findAll(client)
                .map(Response::ok)
                .map(Response.ResponseBuilder::build);
    }

    @GET
    @Path("{id}")
    public Uni<Response> getSingle(@PathParam Long id) {
        return Fruit.findById(client, id)
                .map(fruit -> fruit != null ? Response.ok(fruit) : Response.status(Response.Status.NOT_FOUND))
                .map(Response.ResponseBuilder::build);
    }

    @POST
    public Uni<Response> create(Fruit fruit) {
        return fruit.save(client)
                .map(id -> URI.create("/fruits/" + id))
                .map(uri -> Response.created(uri).build());
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(@PathParam Long id) {
        return Fruit.delete(client, id)
                .map(deleted -> deleted ? Response.Status.NO_CONTENT : Response.Status.NOT_FOUND)
                .map(status -> Response.status(status).build());
    }
}

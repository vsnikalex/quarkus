package com.tsystems.vertx;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class VertxJsonResource {

    @GET
    @Path("{name}/object")
    public JsonObject jsonObject(@PathParam String name) {
        return new JsonObject().put("Hello", name);
    }

    @GET
    @Path("{name}/array")
    public JsonArray jsonArray(@PathParam String name) {
        return new JsonArray().add("Hello").add(name);
    }
}

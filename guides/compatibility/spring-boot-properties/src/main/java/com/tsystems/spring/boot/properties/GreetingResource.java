package com.tsystems.spring.boot.properties;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/greeting")
public class GreetingResource {

    @Inject
    GreetingProperties properties;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return properties.text + ", " + properties.name.orElse("You") + properties.suffix;
    }
}
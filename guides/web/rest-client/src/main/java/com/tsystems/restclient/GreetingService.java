package com.tsystems.restclient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@ApplicationScoped
@RegisterRestClient
public interface GreetingService {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    String hello();
}

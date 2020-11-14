package com.telekom.skswms.async.client;

import io.smallrye.mutiny.Uni;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/relatedParties")
@RegisterRestClient(configKey = "local-api")
public interface RelatedPartyClient {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    Uni<Response> getRelatedParty();
}

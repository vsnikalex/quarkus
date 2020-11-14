package com.telekom.skswms.async.client;

import io.smallrye.mutiny.Uni;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.microprofile.client.ExceptionMapping;

@Path("/hello")
public class FacadeResource {

    @Inject
    @RestClient
    TroubleTicketClient troubleTicketClient;

    @Inject
    @RestClient
    RelatedEntityClient relatedEntityClient;

    @Inject
    @RestClient
    RelatedPartyClient relatedPartyClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @SuppressWarnings("unchecked")
    public Uni<Response> hello() {
        // works with all responses (incl. 404) when microprofile.rest.client.disable.default.mapper=true
        Uni<Response> ticketUni = troubleTicketClient.getTroubleTicket()
                .onItem().transform(response -> {
                    if (response.getStatus() == 404) {
                        return Response.fromResponse(response).entity("MS not available").build();
                    } else {
                        return response;
                    }
                });

        Uni<Response> relatedEntityUni = relatedEntityClient.getRelatedEntity();

        Uni<Response> relatedPartyUni = relatedPartyClient.getRelatedParty();

        List<Uni<Response>> list = Arrays.asList(ticketUni, relatedEntityUni, relatedPartyUni);

        return Uni.combine().all().unis(list).combinedWith(results -> {
            List<Response> responseList = (List<Response>) results;

            // TODO: Quesion #7: status code in the case of partial failure
            int status = responseList.stream()
                    .map(Response::getStatus)
                    .max(Integer::compare)
                    .get();

            // TODO: Quesion #8: response format (acc. TMF: full ticket representation)
            String body = responseList.stream()
                    .map(response -> "" + response.getStatus())
                    .collect(Collectors.joining("\n"));

            return Response.status(status).entity(body).build();
        });
    }
}

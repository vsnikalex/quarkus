package com.tsystems.kafka.streams.aggregator.rest;

import com.tsystems.kafka.streams.aggregator.streams.GetWeatherStationDataResult;
import com.tsystems.kafka.streams.aggregator.streams.InteractiveQueries;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@ApplicationScoped
@Path("/weather-stations")
public class WeatherStationEndpoint {

    @Inject
    InteractiveQueries interactiveQueries;

    @GET
    @Path("/data/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeatherStationData(@PathParam("id") int id) {
        GetWeatherStationDataResult result = interactiveQueries.getWeatherStationData(id);

        if (result.getResult().isPresent()) {
            return Response.ok(result.getResult().get()).build();
        }
        else {
            return Response.status(Status.NOT_FOUND.getStatusCode(),
                    "No data found for weather station " + id).build();
        }
    }
}

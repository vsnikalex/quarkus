package com.telekom.design.behavioral.state;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/player")
public class PlayerResource {

    private Player player;

    @PostConstruct
    void init() {
        player = new Player();
    }

    @GET
    @Path("/play")
    @Produces(MediaType.TEXT_PLAIN)
    public String play() {
        return player.getState().onPlay();
    }

    @GET
    @Path("/stop")
    @Produces(MediaType.TEXT_PLAIN)
    public String stop() {
        return player.getState().onLock();
    }

    @GET
    @Path("/next")
    @Produces(MediaType.TEXT_PLAIN)
    public String next() {
        return player.getState().onNext();
    }

    @GET
    @Path("/prev")
    @Produces(MediaType.TEXT_PLAIN)
    public String prev() {
        return player.getState().onPrevious();
    }
}

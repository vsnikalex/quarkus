package com.tsystems.lazy;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/ping")
public class PingResource {

    @Inject
    AmazingService s1;

    @Inject
    CoolService s2;

    @GET
    public String ping() {
        return s1.ping() + s2.ping();
    }
}

package com.tsystems;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/gelf-logging")
@ApplicationScoped
public class GelfLoggingResource {
    private static final Logger LOG = Logger.getLogger(GelfLoggingResource.class);

    @GET
    public void log() {
        LOG.info("Some useful log message");
    }

}
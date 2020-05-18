package com.tsystems.vault;

import io.quarkus.vault.VaultKVSecretEngine;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "a-private-key")
    String privateKey;

    @Inject
    VaultKVSecretEngine kvSecretEngine;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("/private-key")
    @Produces(MediaType.TEXT_PLAIN)
    public String privateKey() {
        return privateKey;
    }

    @GET
    @Path("/secrets/{vault-path}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSecrets(@PathParam("vault-path") String vaultPath) {
        return kvSecretEngine.readSecret("myapps/vault-quickstart/" + vaultPath).toString();
    }
}
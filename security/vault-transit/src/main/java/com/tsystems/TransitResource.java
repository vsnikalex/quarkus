package com.tsystems;

import io.quarkus.vault.VaultTransitSecretEngine;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/transit")
@Produces(TEXT_PLAIN)
@Consumes(TEXT_PLAIN)
public class TransitResource {

    @Inject
    public VaultTransitSecretEngine transitSecretEngine;

    @POST
    @Path("/encrypt")
    public String encrypt(String clearData) {
        return transitSecretEngine.encrypt("my-encryption-key", clearData);
    }

    @POST
    @Path("/decrypt")
    public String decrypt(String cipherText) {
        return transitSecretEngine.decrypt("my-encryption-key", cipherText).asString();
    }
}

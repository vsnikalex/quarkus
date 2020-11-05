package com.tsystems;

import io.quarkus.vault.VaultTransitSecretEngine;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
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

    @POST
    @Path("/sign")
    public String sign(String input) {
        return transitSecretEngine.sign("my-sign-key", input);
    }

    @POST
    @Path("/verify")
    @Consumes(APPLICATION_JSON)
    public Response verify(VerifyRequest request) {
        transitSecretEngine.verifySignature("my-sign-key", request.signature, request.input);
        return Response.accepted().build();
    }

    public static class VerifyRequest {
        public String signature;
        public String input;
    }
}

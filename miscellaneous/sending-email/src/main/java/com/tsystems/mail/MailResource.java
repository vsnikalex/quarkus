package com.tsystems.mail;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;

@Path("/mail")
public class MailResource {

    @Inject
    ReactiveMailer reactiveMailer;

    @GET
    @Path("/async")
    public CompletionStage<Response> sendASimpleEmailAsync() {
        return reactiveMailer.send(
                Mail.withText("repnikum@gmail.com", "A reactive email from quarkus", "This is my body"))
                .subscribeAsCompletionStage()
                .thenApply(x -> Response.accepted().build());
    }

    @GET
    @Path("/attachment")
    public Response sendEmailWithAttachment() {
        reactiveMailer.send(Mail.withText("repnikum@gmail.com", "An email from quarkus with attachment",
                "This is my body")
                .addAttachment("my-file.txt",
                        "content of my file".getBytes(), "text/plain"));
        return Response.accepted().build();
    }


}
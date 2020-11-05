package com.tsystems.mail;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.MailTemplate;
import io.quarkus.mailer.reactive.ReactiveMailer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.concurrent.CompletionStage;

@Path("/mail")
public class MailResource {

    @Inject
    ReactiveMailer reactiveMailer;

    @Inject
    MailTemplate hello;

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

    @GET
    @Path("/html")
    public Response sendingHTML() {
        String body = "<strong>Hello!</strong>" + "\n" +
                "<p>Here is an image for you: <img src=\"cid:my-image@quarkus.io\"/></p>" +
                "<p>Regards</p>";
        reactiveMailer.send(Mail.withHtml("repnikum@gmail.com", "An email in HTML", body)
                .addInlineAttachment("quarkus-logo.png",
                        new File("quarkus-logo.png"),
                        "image/png", "<my-image@quarkus.io>"));
        return Response.accepted().build();
    }

    @GET
    @Path("/template")
    public CompletionStage<Response> send() {
        return hello.to("repnikum@gmail.com")
                .subject("Hello from Qute template")
                // the template looks like: Hello {name}!
                .data("name", "John")
                .send()
                .thenApply(x -> Response.accepted().build());
    }
}
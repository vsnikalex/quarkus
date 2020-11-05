package com.tsystems.mail;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static io.netty.util.internal.SystemPropertyUtil.contains;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

@QuarkusTest
class MailTest {

    private static final String TO = "foo@quarkus.io";

    @Inject
    MockMailbox mailbox;

    @BeforeEach
    void init() {
        mailbox.clear();
    }

    @Test
    void testTextMail() {
        // call a REST endpoint that sends email
        given()
                .when()
                .get("/async")
                .then()
                .statusCode(202)
                .body(is("OK"));

        // verify that it was sent
        List<Mail> sent = mailbox.getMessagesSentTo(TO);
        assertThat(sent, hasSize(1));
        Mail actual = sent.get(0);
        assertThat(actual.getText(), contains("Wake up!"));
        assertThat(actual.getSubject(), equalTo("Alarm!"));

        assertThat(mailbox.getTotalMessagesSent(), equalTo(6));
    }
}
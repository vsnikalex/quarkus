package com.telekom.saga.order;

import com.telekom.saga.order.dto.CustomerDTO;
import io.quarkus.qute.Template;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.reactive.messaging.connectors.InMemoryConnector;
import io.smallrye.reactive.messaging.connectors.InMemorySink;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.enterprise.inject.Any;
import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class SagaTest {

    @Inject
    Template order;

    // 1. Switch the channels to the in-memory connector:
    @BeforeAll
    public static void switchMyChannels() {
        InMemoryConnector.switchOutgoingChannelsToInMemory("customer-create");
    }

    // 2. Don't forget to reset the channel after the tests:
    @AfterAll
    public static void revertMyChannels() {
        InMemoryConnector.clear();
    }

    // 3. Inject the in-memory connector in your test,
    // or use the bean manager to retrieve the instance
    @Inject @Any
    InMemoryConnector connector;

    @Test
    void orderAccepted() {
        // 4. Retrieves the in-memory sink to check what is received
        InMemorySink<CustomerDTO> results = connector.sink("customer-create");

        // 5. Trigger a saga
        given()
                .body(order.render())
                .contentType(ContentType.JSON)
            .when()
                .get("/createOrder")
            .then()
                .statusCode(202)
                .body(is("Order Accepted"));

        // 6. Check you have receives the expected messages
        Assertions.assertEquals(1, results.received().size());
    }
}

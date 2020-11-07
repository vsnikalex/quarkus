package com.telekom.saga.order;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class OrderResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/createOrder")
          .then()
             .statusCode(200)
             .body(is("order created"));
    }
}
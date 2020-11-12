package com.telekom.design.behavioral.state;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PlayerResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/player")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}
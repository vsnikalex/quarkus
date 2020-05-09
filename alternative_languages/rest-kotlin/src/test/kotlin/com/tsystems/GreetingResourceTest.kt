package com.tsystems

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.core.IsEqual.equalTo
import org.junit.jupiter.api.Test


@QuarkusTest
class GreetingResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
                .`when`().get("/greeting")
                .then()
                .statusCode(200)
                .body("message", equalTo("hello"))
    }

}
package com.tsystems.restclient;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@QuarkusTest
public class GreetingResourceTest {

    @InjectMock
    @RestClient
    GreetingService greetingService;

    @Test
    public void testHelloEndpoint() {
        Mockito.when(greetingService.hello()).thenReturn("hello from mockito");

        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("hello from mockito"));
    }

}

package com.tsystems.test;

import io.quarkus.test.Mock;

import javax.enterprise.context.ApplicationScoped;

@Mock
@ApplicationScoped
public class MockedGreetingService extends GreetingService {

    @Override
    public String greeting(String name) {
        return "mocked hello " + name;
    }

}

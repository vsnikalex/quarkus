package com.tsystems.vertx;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.vertx.axle.core.eventbus.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.Executor;

@ApplicationScoped
public class GreetingService {

    @Inject
    Executor executor;

    @ConsumeEvent("greeting")
    public String greeting(String name) {
        return "Hello " + name;
    }

    @ConsumeEvent("greeting1")
    public String consume(String name) {
        return name.toUpperCase();
    }

    @ConsumeEvent("greeting2")
    public Uni<String> consume2(String name) {
        return Uni.createFrom().item(name::toUpperCase).emitOn(executor);
    }

    @ConsumeEvent("greeting3")
    public void consume(Message<String> msg) {
        System.out.println(msg.address());
        System.out.println(msg.body());
    }
}

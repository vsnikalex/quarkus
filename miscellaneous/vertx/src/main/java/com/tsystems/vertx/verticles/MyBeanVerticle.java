package com.tsystems.vertx.verticles;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyBeanVerticle extends AbstractVerticle {

    @ConfigProperty(name = "address")
    String address;

    @Override
    public Uni<Void> asyncStart() {
        return vertx.eventBus().consumer(address)
                .handler(m -> m.replyAndForget("hello"))
                .completionHandler();
    }
}

package com.tsystems.routes;

import io.quarkus.vertx.web.RouteFilter;
import io.vertx.ext.web.RoutingContext;

public class MyFilters {

    @RouteFilter(100)
    void myFilter(RoutingContext rc) {
        rc.response().putHeader("X-Header", "intercepting the request");
        rc.next();
    }
}

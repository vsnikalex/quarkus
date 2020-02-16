package com.tsystems.context;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.awt.*;

@ApplicationScoped
public class CounterBean {

//    @Inject
//    CounterService counterService;

    void onMessage(@Observes Event msg) {
    }
}

package com.tsystems.quarkus.sample;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;

@ApplicationScoped
public class SampleService {

    public Sample get() {
        return new Sample("sample", BigDecimal.valueOf(Math.random()));
    }
}

package com.tsystems.quarkus.sample;

import java.math.BigDecimal;

public class Sample {
    public String name;
    public BigDecimal price;

    public Sample(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}

package com.tsystems.spring.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("greeting")
public class GreetingProperties {

    public String text;
}

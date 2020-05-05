package com.tsystems.spring.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Optional;

@ConfigurationProperties("greeting")
public class GreetingProperties {

    public String text;

    public String suffix = "!";

    public Optional<String> name;
}

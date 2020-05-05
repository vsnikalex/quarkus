package com.tsystems.spring.security;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    @Secured("admin")
    public String hello() {
        return "hello";
    }
}
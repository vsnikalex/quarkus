package com.tsystens.infispan;

import lombok.Data;

import java.util.Objects;

@Data
public class Author {
    private final String name;
    private final String surname;

    public Author(String name, String surname) {
        this.name = Objects.requireNonNull(name);
        this.surname = Objects.requireNonNull(surname);
    }
}

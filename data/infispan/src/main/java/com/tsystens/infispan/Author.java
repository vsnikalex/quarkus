package com.tsystens.infispan;

import lombok.Data;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.Objects;

@Data
public class Author {
    private final String name;
    private final String surname;

    @ProtoFactory
    public Author(String name, String surname) {
        this.name = Objects.requireNonNull(name);
        this.surname = Objects.requireNonNull(surname);
    }

    @ProtoField(number = 1)
    public String getName() {
        return name;
    }

    @ProtoField(number = 2)
    public String getSurname() {
        return surname;
    }
}

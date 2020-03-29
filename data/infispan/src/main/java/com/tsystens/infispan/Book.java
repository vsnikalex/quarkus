package com.tsystens.infispan;

import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Data
public class Book {
    private final String title;
    private final String description;
    private final int publicationYear;
    private final Set<Author> authors;

    public Book(String title, String description, int publicationYear, Set<Author> authors) {
        this.title = Objects.requireNonNull(title);
        this.description = Objects.requireNonNull(description);
        this.publicationYear = publicationYear;
        this.authors = Objects.requireNonNull(authors);
    }
}

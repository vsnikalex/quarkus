package com.tsystems.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;

public class Book {

    @NotBlank(message="Title may not be blank")
    public String title;

    @NotBlank(message="Author may not be blank")
    public String author;

    @Min(message="Author has been very lazy", value=1)
    public double pages;
}
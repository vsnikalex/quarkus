package com.tsystems.hibernate.search.elasticsearch.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@Indexed
public class Book extends PanacheEntity {

    @FullTextField(analyzer = "english")
    public String title;

    @ManyToOne
    @JsonbTransient
    public Author author;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }

        Book other = (Book) o;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}

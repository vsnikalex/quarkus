package com.tsystems.hibernate.search.elasticsearch;

import com.tsystems.hibernate.search.elasticsearch.model.Author;
import com.tsystems.hibernate.search.elasticsearch.model.Book;
import io.quarkus.runtime.StartupEvent;
import org.hibernate.search.mapper.orm.Search;
import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/library")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LibraryResource {

    @Inject
    EntityManager em;

    @PUT
    @Path("book")
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addBook(@FormParam String title, @FormParam Long authorId) {
        Author author = Author.findById(authorId);
        if (author == null) {
            return;
        }

        Book book = new Book();
        book.title = title;
        book.author = author;
        book.persist();

        author.books.add(book);
        author.persist();
    }

    @DELETE
    @Path("book/{id}")
    @Transactional
    public void deleteBook(@PathParam Long id) {
        Book book = Book.findById(id);
        if (book != null) {
            book.author.books.remove(book);
            book.delete();
        }
    }

    @PUT
    @Path("author")
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addAuthor(@FormParam String firstName, @FormParam String lastName) {
        Author author = new Author();
        author.firstName = firstName;
        author.lastName = lastName;
        author.persist();
    }

    @POST
    @Path("author/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateAuthor(@PathParam Long id, @FormParam String firstName, @FormParam String lastName) {
        Author author = Author.findById(id);
        if (author == null) {
            return;
        }
        author.firstName = firstName;
        author.lastName = lastName;
        author.persist();
    }

    @DELETE
    @Path("author/{id}")
    @Transactional
    public void deleteAuthor(@PathParam Long id) {
        Author author = Author.findById(id);
        if (author != null) {
            author.delete();
        }
    }

    @Transactional
    void onStart(@Observes StartupEvent ev) throws InterruptedException {
        // only reindex if we imported some content
        if (Book.count() > 0) {
            Search.session(em)
                    .massIndexer()
                    .startAndWait();
        }
    }

    @GET
    @Path("author/search")
    @Transactional
    public List<Author> searchAuthors(@QueryParam String pattern,
                                      @QueryParam Optional<Integer> size) {
        return Search.session(em)
                .search(Author.class)
                .where(f ->
                        pattern == null || pattern.trim().isEmpty() ?
                                f.matchAll() :
                                f.simpleQueryString()
                                        .fields("firstName", "lastName", "books.title").matching(pattern)
                )
                .sort(f -> f.field("lastName_sort").then().field("firstName_sort"))
                .fetchHits(size.orElse(20));
    }
}
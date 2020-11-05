package com.tsystems.panache.active;

import com.tsystems.panache.Status;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class PersonActExamples {

    public static void main(String[] args) {
        // BASIC EXAMPLES
        // creating a person
        PersonAct person = new PersonAct();
        person.name = "Stef";
        person.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.ALIVE;

        // persist it
        person.persist();

        // note that once persisted, you don't need to explicitly save your entity: all
        // modifications are automatically persisted on transaction commit.

        // check if it's persistent
        if(person.isPersistent()){
            // delete it
            person.delete();
        }

        // getting a list of all Person entities
        List<PersonAct> allPersons = PersonAct.listAll();

        // finding a specific person by ID
        Long personId = 1L;
        person = PersonAct.findById(personId);

        // finding a specific person by ID via an Optional
        Optional<PersonAct> optional = PersonAct.findByIdOptional(personId);
        person = optional.orElseThrow(NotFoundException::new);

        // finding all living persons
        List<PersonAct> livingPersons = PersonAct.list("status", Status.ALIVE);

        // counting all persons
        long countAll = PersonAct.count();

        // counting all living persons
        long countAlive = PersonAct.count("status", Status.ALIVE);

        // delete all living persons
        PersonAct.delete("status", Status.ALIVE);

        // delete all persons
        PersonAct.deleteAll();

        // update all living persons
        PersonAct.update("name = 'Moral' where status = ?1", Status.ALIVE);

        // all list methods have equivalent stream versions
        // the stream methods require a transaction to work
        try (Stream<PersonAct> persons = PersonAct.streamAll()) {
            List<String> namesButEmmanuels = persons
                    .map(p -> p.name.toLowerCase() )
                    .filter( n -> ! "emmanuel".equals(n) )
                    .collect(Collectors.toList());
        }

        // ADVANCED QUERY
        // PAGING
        // create a query for all living persons
        PanacheQuery<PersonAct> livingPersons2 = PersonAct.find("status", Status.ALIVE);

        // make it use pages of 25 entries at a time
        livingPersons2.page(Page.ofSize(25));

        // get the first page
        List<PersonAct> firstPage = livingPersons2.list();

        // get the second page
        List<PersonAct> secondPage = livingPersons2.nextPage().list();

        // get page 7
        List<PersonAct> page7 = livingPersons2.page(Page.of(7, 25)).list();

        // get the number of pages
        int numberOfPages = livingPersons2.pageCount();

        // get the total number of entities returned by this query without paging
        long count = livingPersons2.count();

        // and you can chain methods of course
        Stream<PanacheEntityBase> panacheEntityBaseStream = PersonAct.find("status", Status.ALIVE)
                                                                        .page(Page.ofSize(25))
                                                                        .nextPage()
                                                                        .stream();

        // SORTING
        List<PersonAct> persons = PersonAct.list("order by name,birth");
        persons = PersonAct.list(Sort.by("name").and("birth").toString());

        // and with more restrictions
        persons = PersonAct.list("status", Sort.by("name").and("birth"), Status.ALIVE);

        // QUERY PARAMETERS
        // You can pass query parameters by index (1-based) as shown below:
        PersonAct.find("name = ?1 and status = ?2", "stef", Status.ALIVE);

        // Or by name using a Map:
        Map<String, Object> params = new HashMap<>();
        params.put("name", "stef");
        params.put("status", Status.ALIVE);
        PersonAct.find("name = :name and status = :status", params);

        // generate a Map
        PersonAct.find("name = :name and status = :status",
                Parameters.with("name", "stef").and("status", Status.ALIVE).map());

        // use it as-is
        PersonAct.find("name = :name and status = :status",
                Parameters.with("name", "stef").and("status", Status.ALIVE));
    }
}

package com.tsystems.panache;

import javax.ws.rs.NotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonExamples {

    public static void main(String[] args) {
        // creating a person
        Person person = new Person();
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
        List<Person> allPersons = Person.listAll();

        // finding a specific person by ID
        Long personId = 1L;
        person = Person.findById(personId);

        // finding a specific person by ID via an Optional
        Optional<Person> optional = Person.findByIdOptional(personId);
        person = optional.orElseThrow(NotFoundException::new);

        // finding all living persons
        List<Person> livingPersons = Person.list("status", Status.ALIVE);

        // counting all persons
        long countAll = Person.count();

        // counting all living persons
        long countAlive = Person.count("status", Status.ALIVE);

        // delete all living persons
        Person.delete("status", Status.ALIVE);

        // delete all persons
        Person.deleteAll();

        // update all living persons
        Person.update("name = 'Moral' where status = ?1", Status.ALIVE);

        // all list methods have equivalent stream versions
        // the stream methods require a transaction to work
        try (Stream<Person> persons = Person.streamAll()) {
            List<String> namesButEmmanuels = persons
                    .map(p -> p.name.toLowerCase() )
                    .filter( n -> ! "emmanuel".equals(n) )
                    .collect(Collectors.toList());
        }
    }
}

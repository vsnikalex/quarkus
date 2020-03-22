package com.tsystems.panache.active;

import com.tsystems.panache.Status;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class PersonActExamples {

    public static void main(String[] args) {
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

    }
}

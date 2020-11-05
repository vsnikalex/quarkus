package com.tsystems.panache.repository;

import com.tsystems.panache.Status;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class PersonRepExamples {

    @Inject
    PersonRepository personRepository;

    public void produceExampleCases() {
        // creating a person
        PersonRep person = new PersonRep();
        person.name = "Stef";
        person.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.ALIVE;

        // persist it
        personRepository.persist(person);

        // note that once persisted, you don't need to explicitly save your entity: all
        // modifications are automatically persisted on transaction commit.

        // check if it's persistent
        if(personRepository.isPersistent(person)){
            // delete it
            personRepository.delete(person);
        }

        // getting a list of all Person entities
        List<PersonRep> allPersons = personRepository.listAll();

        // finding a specific person by ID
        Long personId = 1L;
        person = personRepository.findById(personId);

        // finding a specific person by ID via an Optional
        Optional<PersonRep> optional = personRepository.findByIdOptional(personId);
        person = optional.orElseThrow(NotFoundException::new);

        // finding all living persons
        List<PersonRep> livingPersons = personRepository.list("status", Status.ALIVE);

        // counting all persons
        long countAll = personRepository.count();

        // counting all living persons
        long countAlive = personRepository.count("status", Status.ALIVE);

        // delete all living persons
        personRepository.delete("status", Status.ALIVE);

        // delete all persons
        personRepository.deleteAll();

        // update all living persons
        personRepository.update("name = 'Moral' where status = ?1", Status.ALIVE);

        // all list methods have equivalent stream versions
        // the stream methods require a transaction to work
        Stream<PersonRep> persons = personRepository.streamAll();
        List<String> namesButEmmanuels = persons
                .map(p -> p.name.toLowerCase() )
                .filter( n -> ! "emmanuel".equals(n) )
                .collect(Collectors.toList());
    }
}

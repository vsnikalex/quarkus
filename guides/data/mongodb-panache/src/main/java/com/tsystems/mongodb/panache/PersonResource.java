package com.tsystems.mongodb.panache;

import com.tsystems.mongodb.panache.repository.Person;
import com.tsystems.mongodb.panache.repository.PersonRepository;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/persons")
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @GET
    public long count(){
        return personRepository.count();
    }

    private void repositoryExamples() {
        // creating a person
        Person person = new Person();
        person.name = "Loïc";
        person.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.Alive;

        // persist it
        personRepository.persist(person);

        person.status = Status.Dead;

        // Your must call update() in order to send your entity modifications to MongoDB
        personRepository.update(person);

        // delete it
        personRepository.delete(person);

        // getting a list of all Person entities
        List<Person> allPersons = personRepository.listAll();

        ObjectId personId = new ObjectId("1abc");
        // finding a specific person by ID
        person = personRepository.findById(personId);

        // finding a specific person by ID via an Optional
        Optional<Person> optional = personRepository.findByIdOptional(personId);
        person = optional.orElseThrow(() -> new NotFoundException());

        // finding all living persons
        List<Person> livingPersons = personRepository.list("status", Status.Alive);

        // counting all persons
        long countAll = personRepository.count();

        // counting all living persons
        long countAlive = personRepository.count("status", Status.Alive);

        // delete all living persons
        personRepository.delete("status", Status.Alive);

        // delete all persons
        personRepository.deleteAll();

        // all list methods have equivalent stream versions
        Stream<Person> persons = personRepository.streamAll();
        List<String> namesButEmmanuels = persons
                .map(p -> p.name.toLowerCase() )
                .filter( n -> ! "emmanuel".equals(n) )
                .collect(Collectors.toList());
    }
}
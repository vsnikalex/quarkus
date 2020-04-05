package com.tsystems.mongodb.panache.active;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.ws.rs.NotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@MongoEntity(collection="ThePerson")
public class Person extends PanacheMongoEntity {
    public String name;

    // will be persisted as a 'birth' field in MongoDB
    @BsonProperty("birth")
    public LocalDate birth;

    public Status status;

    // return name as uppercase in the model
    public String getName(){
        return name.toUpperCase();
    }

    // store all names in lowercase in the DB
    public void setName(String name){
        this.name = name.toLowerCase();
    }

    public static Person findByName(String name){
        return find("name", name).firstResult();
    }

    public static List<Person> findAlive(){
        return list("status", Status.Alive);
    }

    public static void deleteLoics(){
        delete("name", "Loïc");
    }

    public static void main(String[] args) {
        // creating a person
        Person person = new Person();
        person.name = "Loïc";
        person.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.Alive;

        // persist it
        person.persist();

        person.status = Status.Dead;

        // Your must call update() in order to send your entity modifications to MongoDB
        person.update();

        // delete it
        person.delete();

        // getting a list of all Person entities
        List<Person> allPersons = Person.listAll();

        long personId = 1L;
        // finding a specific person by ID
        person = Person.findById(personId);

        // finding a specific person by ID via an Optional
        Optional<Person> optional = Person.findByIdOptional(personId);
        person = optional.orElseThrow(() -> new NotFoundException());

        // finding all living persons
        List<Person> livingPersons = Person.list("status", Status.Alive);

        // counting all persons
        long countAll = Person.count();

        // counting all living persons
        long countAlive = Person.count("status", Status.Alive);

        // delete all living persons
        Person.delete("status", Status.Alive);

        // delete all persons
        Person.deleteAll();
    }
}

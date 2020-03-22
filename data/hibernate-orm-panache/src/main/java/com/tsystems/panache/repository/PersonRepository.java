package com.tsystems.panache.repository;

import com.tsystems.panache.Status;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<PersonRep> {

    // put your custom logic here as instance methods

    public PersonRep findByName(String name){
        return find("name", name).firstResult();
    }

    public List<PersonRep> findAlive(){
        return list("status", Status.ALIVE);
    }

    public void deleteStefs(){
        delete("name", "Stef");
    }
}

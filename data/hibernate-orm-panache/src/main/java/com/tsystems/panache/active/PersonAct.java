package com.tsystems.panache.active;

import com.tsystems.panache.Status;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class PersonAct extends PanacheEntity {
    public String name;
    public LocalDate birth;
    public Status status;

    public static PersonAct findByName(String name){
        return find("name", name).firstResult();
    }

    public static List<PersonAct> findAlive(){
        return list("status", Status.ALIVE);
    }

    public static void deleteStefs(){
        delete("name", "Stef");
    }
}

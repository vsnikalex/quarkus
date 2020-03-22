package com.tsystems.panache.active;

import com.tsystems.panache.Status;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class PersonAct extends PanacheEntityBase {

    // You can specify your own ID strategy
    @Id
    @SequenceGenerator(
            name = "personSequence",
            sequenceName = "person_id_seq",
            allocationSize = 1,
            initialValue = 4)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSequence")
    public Integer id;

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

package com.tsystems.panache.repository;

import com.tsystems.panache.Status;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class PersonRep extends PanacheEntityBase {
    @Id
    @GeneratedValue
    public Long id;
    public String name;
    public LocalDate birth;
    public Status status;

}

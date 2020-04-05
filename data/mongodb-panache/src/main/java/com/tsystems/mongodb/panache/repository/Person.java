package com.tsystems.mongodb.panache.repository;

import com.tsystems.mongodb.panache.Status;
import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@MongoEntity(collection="TheRepositoryPerson")
public class Person  {
    public ObjectId id; // used by MongoDB for the _id field
    public String name;
    public LocalDate birth;
    public Status status;

}

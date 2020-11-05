package com.tsystems.mongodb.codec;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.tsystems.mongodb.Fruit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CodecFruitService {

    @Inject MongoClient mongoClient;

    public List<Fruit> list(){
        List<Fruit> list = new ArrayList<>();
        MongoCursor<Fruit> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                list.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    public void add(Fruit fruit){
        getCollection().insertOne(fruit);
    }

    private MongoCollection<Fruit> getCollection(){
        return mongoClient.getDatabase("fruit").getCollection("fruit", Fruit.class);
    }
}
package com.tsystems.vertx;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

import java.util.ArrayList;
import java.util.List;

public class Fruit {

    public Long id;

    public String name;

    public Fruit() {
    }

    public Fruit(String name) {
        this.name = name;
    }

    public Fruit(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Fruit from(io.vertx.mutiny.sqlclient.Row row) {
        return new Fruit(row.getLong("id"), row.getString("name"));
    }

    public static Uni<List<Fruit>> findAll(PgPool client) {
        return client.query("SELECT id, name FROM fruits ORDER BY name ASC")
                .map(pgRowSet -> {
                    List<Fruit> list = new ArrayList<>(pgRowSet.size());
                    for (Row row : pgRowSet) {
                        list.add(from(row));
                    }
                    return list;
                });
    }

    public static Uni<Fruit> findById(PgPool client, Long id) {
        return client.preparedQuery("SELECT id, name FROM fruits WHERE id = $1", Tuple.of(id))
                .map(RowSet::iterator)
                .map(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
    }

    public Uni<Long> save(PgPool client) {
        return client.preparedQuery("INSERT INTO fruits (name) VALUES ($1) RETURNING (id)", Tuple.of(name))
                .map(pgRowSet -> pgRowSet.iterator().next().getLong("id"));
    }

    public static Uni<Boolean> delete(PgPool client, Long id) {
        return client.preparedQuery("DELETE FROM fruits WHERE id = $1", Tuple.of(id))
                .map(pgRowSet -> pgRowSet.rowCount() == 1);
    }
}

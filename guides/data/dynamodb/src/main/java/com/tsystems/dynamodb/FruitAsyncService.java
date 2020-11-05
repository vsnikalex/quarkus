package com.tsystems.dynamodb;

import io.smallrye.mutiny.Uni;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FruitAsyncService extends AbstractService {

    @Inject
    DynamoDbAsyncClient dynamoDB;

    public Uni<List<Fruit>> findAll() {
        return Uni.createFrom().completionStage(() -> dynamoDB.scan(scanRequest()))
                .onItem().apply(res -> res.items().stream().map(Fruit::from).collect(Collectors.toList()));
    }

    public Uni<List<Fruit>> add(Fruit fruit) {
        return Uni.createFrom().completionStage(() -> dynamoDB.putItem(putRequest(fruit)))
                .onItem().ignore().andSwitchTo(this::findAll);
    }

    public Uni<Fruit> get(String name) {
        return Uni.createFrom().completionStage(() -> dynamoDB.getItem(getRequest(name)))
                .onItem().apply(resp -> Fruit.from(resp.item()));
    }
}
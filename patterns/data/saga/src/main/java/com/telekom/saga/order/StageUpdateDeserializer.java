package com.telekom.saga.order;

import com.telekom.saga.order.dto.StageUpdate;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class StageUpdateDeserializer extends ObjectMapperDeserializer<StageUpdate> {

    public StageUpdateDeserializer() {
        super(StageUpdate.class);
    }
}

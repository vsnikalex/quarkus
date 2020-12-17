package com.telekom.saga.order;

import com.telekom.saga.order.dto.StateUpdate;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class StateUpdateDeserializer extends ObjectMapperDeserializer<StateUpdate> {

    public StateUpdateDeserializer() {
        super(StateUpdate.class);
    }
}

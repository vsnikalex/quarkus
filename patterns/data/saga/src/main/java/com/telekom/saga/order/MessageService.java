package com.telekom.saga.order;

import com.telekom.saga.order.dto.StateUpdate;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MessageService {

    @Inject
    @Channel("state-updates-stream")
    Emitter<StateUpdate> stateUpdatesEmitter;

    @Incoming("order-stages")
    public void onMessage(StateUpdate stateUpdate) {
        // Find saga by order event and notify about stage result
        OrderResource.createOrderSagas.get(stateUpdate.getOrderId()).onMessage(stateUpdate);

        // Emit SSE for frontend
        stateUpdatesEmitter.send(stateUpdate);
    }
}

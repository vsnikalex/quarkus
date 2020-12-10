package com.telekom.saga.order;

import com.telekom.saga.order.dto.StageUpdate;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessageService {

    @Incoming("order-stages")
    public void onMessage(StageUpdate stageUpdate) {
        // TODO: find sga by orderId
        OrderResource.createOrderSagas.get(0).onMessage(stageUpdate);

        // TODO: emit SSE for frontend
    }
}

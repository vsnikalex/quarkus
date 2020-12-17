package com.telekom.service;

import com.telekom.saga.order.dto.CustomerDTO;
import com.telekom.saga.order.dto.StateUpdate;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.telekom.saga.order.dto.StateUpdate.State.CUSTOMER_VERIFICATION;

@ApplicationScoped
public class CustomerService {

    private static final Logger LOGGER = Logger.getLogger(CustomerService.class);

    Map<String, CustomerDTO> customers = new HashMap<>();

    @Inject
    @Channel("order-reply")
    Emitter<StateUpdate> stageUpdateEmitter;

    @Incoming("customers")
    public void verify(CustomerDTO customerDTO) throws InterruptedException {
        // Persist customer in "database"
        customers.put(customerDTO.getOrderId(), customerDTO);

        final String orderId = customerDTO.getOrderId();
        LOGGER.infof("Order %s: Begin Verification of Customer %s %s", orderId, customerDTO.getName(), customerDTO.getSurname());

        // Validate customer
        boolean success = customerDTO.getPhoneNumber().contains("812");

        // Emulate delay
        Thread.sleep(2000);

        // Emit event
        stageUpdateEmitter.send(new StateUpdate(orderId, CUSTOMER_VERIFICATION, success));
    }
}

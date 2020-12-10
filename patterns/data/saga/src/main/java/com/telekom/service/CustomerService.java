package com.telekom.service;

import com.telekom.saga.order.dto.CustomerDTO;
import com.telekom.saga.order.dto.StageUpdate;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CustomerService {

    private static final Logger LOGGER = Logger.getLogger(CustomerService.class);

    Map<String, CustomerDTO> customers = new HashMap<>();

    @Inject
    @Channel("order-reply")
    Emitter<StageUpdate> stageUpdateEmitter;

    @Incoming("customers")
    public void verify(CustomerDTO customerDTO) {
        customers.put(customerDTO.getOrderId(), customerDTO);

        LOGGER.infof("Order %s: Begin Verification of Customer %s %s",
                customerDTO.getOrderId(), customerDTO.getName(), customerDTO.getSurname());

        stageUpdateEmitter.send(new StageUpdate(StageUpdate.Stage.CUSTOMER_VERIFICATION,
                                                customerDTO.getPhoneNumber().contains("812")));
    }
}

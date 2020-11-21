package com.telekom.service;

import com.telekom.saga.order.dto.CustomerDTO;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CustomerService {

    private static final Logger LOGGER = Logger.getLogger(CustomerService.class);

    Map<String, CustomerDTO> customers = new HashMap<>();

    @Incoming("customers")
    public void verify(CustomerDTO customerDTO) {
        customers.put(customerDTO.getOrderId(), customerDTO);

        LOGGER.infof("Order %s: Begin Verification of Customer %s %s",
                customerDTO.getOrderId(), customerDTO.getName(), customerDTO.getSurname());

        // TODO: emit event CustomerVerified into saga reply channel
    }
}

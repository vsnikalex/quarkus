package com.telekom.saga.order.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@RegisterForReflection
@NoArgsConstructor
@AllArgsConstructor
public class StageUpdate {

    public enum Stage {
        CUSTOMER_VERIFICATION,
        TICKET_CREATION,
        PAYMENT_APPROVAL
    }

    Stage stage;
    boolean success;
}

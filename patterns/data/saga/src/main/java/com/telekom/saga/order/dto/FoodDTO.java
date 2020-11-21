package com.telekom.saga.order.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;

import java.util.List;

@Getter
@RegisterForReflection
public class FoodDTO {

    private List<String> positions;
}

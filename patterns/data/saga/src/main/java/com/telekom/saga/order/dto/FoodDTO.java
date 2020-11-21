package com.telekom.saga.order.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;

import javax.enterprise.inject.Model;
import java.util.List;

@Getter
@RegisterForReflection
@Model
public class FoodDTO {

    List<String> positions;

    String orderId;
}

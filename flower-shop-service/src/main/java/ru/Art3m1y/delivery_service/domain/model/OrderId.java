package ru.Art3m1y.delivery_service.domain.model;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class OrderId {
    private final UUID value;

    public OrderId(UUID value) {
        this.value = Objects.requireNonNull(value, "Order id must not be null");
    }

    public static OrderId fromString(String value) {
        return new OrderId(UUID.fromString(value));
    }
}

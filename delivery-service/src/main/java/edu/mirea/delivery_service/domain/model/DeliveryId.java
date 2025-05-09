package edu.mirea.delivery_service.domain.model;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class DeliveryId {
    private final UUID value;

    public DeliveryId(UUID value) {
        this.value = Objects.requireNonNull(value, "Delivery id must not be null");
    }

    public static DeliveryId fromString(String value) {
        return new DeliveryId(UUID.fromString(value));
    }
}

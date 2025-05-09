package edu.mirea.delivery_service.domain.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ProductCode {
    private final String value;

    public ProductCode(String value) {
        this.value = Objects.requireNonNull(value, "Product code must not be null");
    }
}

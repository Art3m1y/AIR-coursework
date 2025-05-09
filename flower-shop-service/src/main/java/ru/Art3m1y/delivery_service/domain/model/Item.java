package ru.Art3m1y.delivery_service.domain.model;

import lombok.Getter;

@Getter
public class Item {
    private final ProductCode code;
    private final int quantity;

    public Item(String code, int quantity) {
        this.code = new ProductCode(code);
        if (quantity <= 0 || quantity > 100) {
            throw new IllegalArgumentException("Quantity must be between 0 and 100");
        }
        this.quantity = quantity;
    }
}

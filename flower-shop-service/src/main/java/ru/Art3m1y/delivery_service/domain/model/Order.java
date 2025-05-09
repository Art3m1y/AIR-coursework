package ru.Art3m1y.delivery_service.domain.model;

import lombok.Getter;
import lombok.Setter;
import ru.Art3m1y.delivery_service.domain.enumeration.OrderStatus;

import java.util.List;

@Getter
public class Order {
    @Setter
    private OrderId id;
    @Setter
    private DeliveryId deliveryId;
    private final List<Item> items;
    private Recipient recipient;
    private Address address;
    @Setter
    private OrderStatus status;

    public Order(OrderId id, List<Item> items, Recipient recipient, Address address, OrderStatus status) {
        this.deliveryId = deliveryId;
        this.id = id;
        this.recipient = recipient;
        this.address = address;
        this.items = items;
        this.status = status;
    }

    public boolean isInDelivery() {
        return deliveryId != null;
    }
}
package edu.mirea.flower_shop_service.domain.model;

import lombok.Getter;
import lombok.Setter;
import edu.mirea.flower_shop_service.domain.enumeration.OrderStatus;

import java.util.List;

@Getter
public class Order {
    @Setter
    private OrderId id;
    @Setter
    private DeliveryId deliveryId;
    private final List<Item> items;
    private final Recipient recipient;
    private final Address address;
    @Setter
    private OrderStatus status;

    public Order(OrderId id, List<Item> items, Recipient recipient, Address address, OrderStatus status) {
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
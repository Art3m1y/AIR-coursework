package edu.mirea.delivery_service.domain.enumeration;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

import static edu.mirea.delivery_service.domain.enumeration.OrderMarker.withOrder;
import static edu.mirea.delivery_service.domain.enumeration.OrderMarker.withoutOrder;

@AllArgsConstructor
public enum DeliveryStatus {
    NEW(withOrder(1)),
    ASSIGNED(withOrder(2)),
    IN_TRANSIT(withOrder(3)),
    DELIVERED(withOrder(4)),
    CANCELLED(withoutOrder()),
    FAILED(withoutOrder());

    private final OrderMarker orderMarker;

    public static Optional<DeliveryStatus> findNextFor(DeliveryStatus status) {
        var desiredOrder = status.orderMarker.getOrderId() + 1;
        return Arrays.stream(DeliveryStatus.values())
                .filter(currentStatus -> currentStatus.orderMarker.getOrderId() == desiredOrder)
                .findAny();
    }
}

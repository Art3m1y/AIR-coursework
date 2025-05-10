package edu.mirea.delivery_service.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

import static edu.mirea.delivery_service.domain.enumeration.OrderMarker.withOrder;
import static edu.mirea.delivery_service.domain.enumeration.OrderMarker.withoutOrder;

@AllArgsConstructor
public enum DeliveryStatus {
    NEW(withOrder(1), true),
    ASSIGNED(withOrder(2), true),
    IN_TRANSIT(withOrder(3), false),
    DELIVERED(withOrder(4), false),
    CANCELLED(withoutOrder(), false),
    FAILED(withoutOrder(), false);

    private final OrderMarker orderMarker;
    @Getter
    private final boolean isCancellable;

    public static Optional<DeliveryStatus> findNextFor(DeliveryStatus status) {
        if (!status.orderMarker.isWithOrder()) {
            throw new IllegalArgumentException("Статус %s является конечным".formatted(status));
        }
        var desiredOrder = status.orderMarker.getOrderId() + 1;
        return Arrays.stream(DeliveryStatus.values())
                .filter(currentStatus -> currentStatus.orderMarker.getOrderId() == desiredOrder)
                .findAny();
    }
}

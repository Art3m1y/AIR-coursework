package edu.mirea.delivery_service.domain.model;

import edu.mirea.delivery_service.domain.enumeration.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class Delivery {
    @Setter
    private DeliveryId id;
    private Recipient recipient;
    private Address address;
    private List<Item> items;
    @Setter
    private DeliveryStatus status;

    public Delivery(Recipient recipient, Address address, List<Item> items) {
        this.recipient = recipient;
        this.address = address;
        this.items = items;
    }

    public void moveDeliveryStatus() {
        var nextStatusOpt = DeliveryStatus.findNextFor(status);
        if (nextStatusOpt.isEmpty()) {
            throw new IllegalStateException("Доставка находится в конечном состоянии, невозможно перевести статус");
        }
        this.setStatus(nextStatusOpt.get());
    }

    public void cancelDelivery() {
        checkCancelAvailability();
        this.setStatus(DeliveryStatus.CANCELLED);
    }

    private void checkCancelAvailability() {
        if (!status.isCancellable()) {
            throw new IllegalStateException("Доставка в статусе %s не может быть отменена".formatted(status));
        }
    }
}
package edu.mirea.delivery_service.application.port.out;

import edu.mirea.delivery_service.domain.model.Delivery;
import edu.mirea.delivery_service.domain.model.DeliveryId;

import java.util.Optional;

public interface DeliveryPersistencePort {
    Optional<Delivery> findDelivery(DeliveryId id);
    void updateDeliveryState(Delivery delivery);
    DeliveryId addDelivery(Delivery delivery);
}

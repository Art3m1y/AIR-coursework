package edu.mirea.flower_shop_service.application.port.out;

import edu.mirea.flower_shop_service.domain.model.Delivery;

import java.util.Optional;

public interface DeliveryPersistencePort {
    Optional<Delivery> findDelivery(String id);
    void updateDeliveryState(Delivery delivery);
    void addDelivery(Delivery delivery);
}

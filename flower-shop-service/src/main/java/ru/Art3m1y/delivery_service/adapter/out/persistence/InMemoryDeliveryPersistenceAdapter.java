package ru.Art3m1y.delivery_service.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.Art3m1y.delivery_service.application.port.out.DeliveryPersistencePort;
import ru.Art3m1y.delivery_service.domain.model.Delivery;
import ru.Art3m1y.delivery_service.domain.model.DeliveryId;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@AllArgsConstructor
public class InMemoryDeliveryPersistenceAdapter implements DeliveryPersistencePort {
    private final Map<UUID, Delivery> deliveries = new ConcurrentHashMap<>();

    @Override
    public Optional<Delivery> findDelivery(DeliveryId id) {
        return Optional.of(deliveries.get(id.getValue()));
    }

    @Override
    public void updateDeliveryState(Delivery delivery) {
        deliveries.put(delivery.getId().getValue(), delivery);
    }

    @Override
    public DeliveryId addDelivery(Delivery delivery) {
        var id = UUID.randomUUID();
        deliveries.put(id, delivery);
        return new DeliveryId(id);
    }
}

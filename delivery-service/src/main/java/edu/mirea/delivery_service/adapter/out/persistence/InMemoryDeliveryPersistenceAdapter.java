package edu.mirea.delivery_service.adapter.out.persistence;

import edu.mirea.delivery_service.application.port.out.DeliveryPersistencePort;
import edu.mirea.delivery_service.domain.model.Delivery;
import edu.mirea.delivery_service.domain.model.DeliveryId;
import edu.mirea.delivery_service.infrastructure.common.LoggableContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
        LoggableContext.putDeliveryId(id.getValue());
        return Optional.ofNullable(deliveries.get(id.getValue()));
    }

    @Override
    public void updateDeliveryState(Delivery delivery) {
        deliveries.put(delivery.getId().getValue(), delivery);
        log.info("Доставка обновлена");
    }

    @Override
    public DeliveryId addDelivery(Delivery delivery) {
        var id = UUID.randomUUID();
        LoggableContext.putDeliveryId(id);
        deliveries.put(id, delivery);
        log.info("Новая доставка создана");
        return new DeliveryId(id);
    }
}

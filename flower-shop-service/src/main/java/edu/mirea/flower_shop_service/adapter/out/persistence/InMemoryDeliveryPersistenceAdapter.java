package edu.mirea.flower_shop_service.adapter.out.persistence;

import edu.mirea.flower_shop_service.application.port.out.DeliveryPersistencePort;
import edu.mirea.flower_shop_service.domain.model.Delivery;
import edu.mirea.flower_shop_service.infrastructure.common.LoggableContext;
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
    private final Map<String, Delivery> deliveries = new ConcurrentHashMap<>();

    @Override
    public Optional<Delivery> findDelivery(String id) {
        LoggableContext.putDeliveryId(UUID.fromString(id));
        return Optional.ofNullable(deliveries.get(id));
    }

    @Override
    public void updateDeliveryState(Delivery delivery) {
        deliveries.put(delivery.getExternalId(), delivery);
        log.info("Доставка обновлена");
    }

    @Override
    public void addDelivery(Delivery delivery) {
        LoggableContext.putDeliveryId(UUID.fromString(delivery.getExternalId()));
        deliveries.put(delivery.getExternalId(), delivery);
        log.info("Новая доставка создана");
    }
}

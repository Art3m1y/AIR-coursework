package edu.mirea.flower_shop_service.adapter.out.persistence;

import edu.mirea.flower_shop_service.application.port.out.OrderPersistencePort;
import edu.mirea.flower_shop_service.domain.model.Order;
import edu.mirea.flower_shop_service.domain.model.OrderId;
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
public class InMemoryOrderPersistenceAdapter implements OrderPersistencePort {
    private final Map<UUID, Order> orders = new ConcurrentHashMap<>();

    @Override
    public Optional<Order> findOrder(OrderId id) {
        LoggableContext.putOrderId(id.getValue());
        return Optional.ofNullable(orders.get(id.getValue()));
    }

    @Override
    public void updateOrderState(Order order) {
        orders.put(order.getId().getValue(), order);
        log.info("Заказ обновлен");
    }

    @Override
    public OrderId addOrder(Order order) {
        var id = UUID.randomUUID();
        LoggableContext.putOrderId(id);
        orders.put(id, order);
        log.info("Новый заказ создан");
        return new OrderId(id);
    }
}

package edu.mirea.flower_shop_service.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import edu.mirea.flower_shop_service.application.port.out.OrderPersistencePort;
import edu.mirea.flower_shop_service.domain.model.Order;
import edu.mirea.flower_shop_service.domain.model.OrderId;
import edu.mirea.flower_shop_service.domain.model.*;

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
        return Optional.ofNullable(orders.get(id.getValue()));
    }

    @Override
    public void updateOrderState(Order order) {
        orders.put(order.getId().getValue(), order);
    }

    @Override
    public OrderId addOrder(Order order) {
        var id = UUID.randomUUID();
        orders.put(id, order);
        return new OrderId(id);
    }
}

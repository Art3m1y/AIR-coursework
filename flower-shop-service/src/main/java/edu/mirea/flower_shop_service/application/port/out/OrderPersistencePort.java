package edu.mirea.flower_shop_service.application.port.out;

import edu.mirea.flower_shop_service.domain.model.Order;
import edu.mirea.flower_shop_service.domain.model.OrderId;

import java.util.Optional;

public interface OrderPersistencePort {
    Optional<Order> findOrder(OrderId id);
    void updateOrderState(Order order);
    OrderId addOrder(Order order);
}

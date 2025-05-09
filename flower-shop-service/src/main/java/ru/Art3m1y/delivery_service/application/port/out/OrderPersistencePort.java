package ru.Art3m1y.delivery_service.application.port.out;

import ru.Art3m1y.delivery_service.domain.model.Order;
import ru.Art3m1y.delivery_service.domain.model.OrderId;

import java.util.Optional;

public interface OrderPersistencePort {
    Optional<Order> findOrder(OrderId id);
    void updateOrderState(Order order);
    OrderId addOrder(Order order);
}

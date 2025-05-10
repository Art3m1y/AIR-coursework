package edu.mirea.flower_shop_service.application.port.in;

import edu.mirea.flower_shop_service.domain.model.Order;

public interface CreateOrderUseCase {
    Order createOrder(CreateOrderCommand command);
}

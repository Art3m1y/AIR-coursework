package ru.Art3m1y.delivery_service.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.Art3m1y.delivery_service.application.port.in.CreateOrderCommand;
import ru.Art3m1y.delivery_service.application.port.in.CreateOrderUseCase;
import ru.Art3m1y.delivery_service.application.port.out.OrderPersistencePort;
import ru.Art3m1y.delivery_service.domain.enumeration.OrderStatus;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService implements CreateOrderUseCase {
    private final OrderPersistencePort orderPersistencePort;

    @Override
    public void createOrder(CreateOrderCommand command) {
        var order = command.getOrder();
        order.setStatus(OrderStatus.NEW);

        var orderId = orderPersistencePort.addOrder(order);
        order.setId(orderId);

        log.info("Создан новый заказ");
    }
}

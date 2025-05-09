package ru.Art3m1y.delivery_service.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.Art3m1y.delivery_service.domain.model.Order;

@Getter
@AllArgsConstructor
public class CreateOrderCommand {
    private final Order order;
}

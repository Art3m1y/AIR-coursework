package edu.mirea.flower_shop_service.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import edu.mirea.flower_shop_service.domain.model.Order;

@Getter
@AllArgsConstructor
public class CreateOrderCommand {
    private final Order order;
}

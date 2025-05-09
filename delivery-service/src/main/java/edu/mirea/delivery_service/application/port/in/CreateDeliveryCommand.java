package edu.mirea.delivery_service.application.port.in;

import edu.mirea.delivery_service.domain.model.Delivery;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateDeliveryCommand {
    private final Delivery delivery;
}

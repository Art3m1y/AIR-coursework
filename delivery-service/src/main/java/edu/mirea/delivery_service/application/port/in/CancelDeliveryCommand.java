package edu.mirea.delivery_service.application.port.in;

import edu.mirea.delivery_service.domain.model.DeliveryId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CancelDeliveryCommand {
    private final DeliveryId id;
}

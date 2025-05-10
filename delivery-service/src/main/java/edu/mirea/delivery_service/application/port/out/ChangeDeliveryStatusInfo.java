package edu.mirea.delivery_service.application.port.out;

import edu.mirea.delivery_service.domain.enumeration.DeliveryStatus;
import edu.mirea.delivery_service.domain.model.DeliveryId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeDeliveryStatusInfo {
    private final DeliveryId deliveryId;
    private final DeliveryStatus deliveryStatus;
    private final String sourceSystem;
}

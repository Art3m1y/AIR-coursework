package ru.Art3m1y.delivery_service.application.port.in;

import lombok.Builder;
import lombok.Getter;
import ru.Art3m1y.delivery_service.domain.model.DeliveryId;

@Builder
@Getter
public class ChangeDeliveryStatusCommand {
    private DeliveryId id;
    private String status;
}

package edu.mirea.flower_shop_service.application.port.in;

import lombok.Builder;
import lombok.Getter;
import edu.mirea.flower_shop_service.domain.model.DeliveryId;

@Builder
@Getter
public class ChangeDeliveryStatusCommand {
    private DeliveryId id;
    private String status;
}

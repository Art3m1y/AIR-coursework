package edu.mirea.flower_shop_service.application.port.in;

import lombok.Builder;
import lombok.Getter;
import edu.mirea.flower_shop_service.domain.model.OrderId;

@Builder
@Getter
public class CancelDeliveryCommand {
    private final OrderId id;
}

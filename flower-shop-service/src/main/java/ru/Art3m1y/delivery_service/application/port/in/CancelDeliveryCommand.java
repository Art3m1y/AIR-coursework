package ru.Art3m1y.delivery_service.application.port.in;

import lombok.Builder;
import lombok.Getter;
import ru.Art3m1y.delivery_service.domain.model.OrderId;

@Builder
@Getter
public class CancelDeliveryCommand {
    private final OrderId id;
}

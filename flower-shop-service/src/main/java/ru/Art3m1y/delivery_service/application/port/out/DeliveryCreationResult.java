package ru.Art3m1y.delivery_service.application.port.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryCreationResult {
    private final String externalId;
    private final String externalStatus;
}

package ru.Art3m1y.delivery_service.application.port.out;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeliveryCancellationInfo {
    private final String id;
}

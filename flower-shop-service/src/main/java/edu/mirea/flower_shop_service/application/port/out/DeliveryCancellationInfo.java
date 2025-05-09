package edu.mirea.flower_shop_service.application.port.out;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeliveryCancellationInfo {
    private final String id;
}

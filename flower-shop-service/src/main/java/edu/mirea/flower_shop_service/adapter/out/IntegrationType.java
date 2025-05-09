package edu.mirea.flower_shop_service.adapter.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@AllArgsConstructor
@Getter
public enum IntegrationType {
    DELIVERY_SERVICE(Names.DELIVERY_SERVICE);

    private final String name;

    @UtilityClass
    public static class Names {
        public static final String DELIVERY_SERVICE = "delivery-service";
    }
}

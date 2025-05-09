package edu.mirea.delivery_service.adapter.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@AllArgsConstructor
@Getter
public enum IntegrationType {
    FLOWER_SHOP_SERVICE(Names.FLOWER_SHOP_SERVICE);

    private final String name;

    @UtilityClass
    public static class Names {
        public static final String FLOWER_SHOP_SERVICE = "flower-shop-service";
    }
}

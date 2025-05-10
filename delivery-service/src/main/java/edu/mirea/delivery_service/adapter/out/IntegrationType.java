package edu.mirea.delivery_service.adapter.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@AllArgsConstructor
@Getter
public enum IntegrationType {
    SOURCE_SYSTEM_SERVICE(Names.SOURCE_SYSTEM_SERVICE);

    private final String name;

    @UtilityClass
    public static class Names {
        public static final String SOURCE_SYSTEM_SERVICE = "source-system-service";
    }
}

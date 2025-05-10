package edu.mirea.delivery_service.infrastructure.common;


import org.slf4j.MDC;

import java.util.UUID;

public class LoggableContext {
    public static final String DELIVERY_ID_LOG_FIELD_NAME = "DELIVERY-ID";

    public static void putDeliveryId(UUID deliveryId) {
        MDC.put(DELIVERY_ID_LOG_FIELD_NAME, deliveryId.toString());
    }

    public static void clear() {
        MDC.clear();
    }
}

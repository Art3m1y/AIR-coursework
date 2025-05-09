package edu.mirea.delivery_service.domain.enumeration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderMarker {
    private Integer orderId;
    private boolean isWithOrder;
    
    public static OrderMarker withoutOrder() {
        return new OrderMarker(null, false);
    }

    public static OrderMarker withOrder(int order) {
        return new OrderMarker(order, true);
    }
}

package edu.mirea.flower_shop_service.application.port.out;

public interface DeliveryServicePort {
    DeliveryCreationResult createDelivery(DeliveryCreationInfo info);
    void cancelDelivery(DeliveryCancellationInfo info);
}

package ru.Art3m1y.delivery_service.application.port.out;

public interface DeliveryServicePort {
    DeliveryCreationResult createDelivery(DeliveryCreationInfo info);
    void cancelDelivery(DeliveryCancellationInfo info);
}

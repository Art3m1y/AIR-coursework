package edu.mirea.delivery_service.application.port.in;

public interface CancelDeliveryUseCase {
    void cancelDelivery(CancelDeliveryCommand command);
}

package edu.mirea.flower_shop_service.application.port.in;

public interface CancelDeliveryUseCase {
    void cancelDeliveryFromOrder(CancelDeliveryCommand command);
}

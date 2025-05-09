package edu.mirea.flower_shop_service.application.port.in;

public interface CreateDeliveryUseCase {
    void createDeliveryFromOrder(CreateDeliveryCommand createDeliveryCommand);
}

package edu.mirea.flower_shop_service.application.port.in;

public interface ChangeDeliveryStatusUseCase {
    void changeStatus(ChangeDeliveryStatusCommand command);
}

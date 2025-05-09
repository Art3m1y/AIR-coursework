package ru.Art3m1y.delivery_service.application.port.in;

public interface ChangeDeliveryStatusUseCase {
    void changeStatus(ChangeDeliveryStatusCommand command);
}

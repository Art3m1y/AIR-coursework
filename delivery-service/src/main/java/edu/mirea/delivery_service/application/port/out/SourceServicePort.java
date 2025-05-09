package edu.mirea.delivery_service.application.port.out;

public interface SourceServicePort {
    void changeStatus(ChangeDeliveryStatusInfo info);
}

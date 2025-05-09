package edu.mirea.delivery_service.application.port.in;

import edu.mirea.delivery_service.domain.model.Delivery;

public interface CreateDeliveryUseCase {
    void createDelivery(CreateDeliveryCommand command);
}

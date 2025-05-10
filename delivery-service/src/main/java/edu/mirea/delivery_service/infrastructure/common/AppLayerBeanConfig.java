package edu.mirea.delivery_service.infrastructure.common;

import edu.mirea.delivery_service.application.DeliveryService;
import edu.mirea.delivery_service.application.port.out.DeliveryPersistencePort;
import edu.mirea.delivery_service.application.port.out.SourceServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppLayerBeanConfig {
    @Bean
    public DeliveryService createDeliveryUseCase(DeliveryPersistencePort deliveryPersistencePort, SourceServicePort sourceServicePort) {
        return new DeliveryService(deliveryPersistencePort, sourceServicePort);
    }
}

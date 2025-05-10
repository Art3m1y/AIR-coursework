package edu.mirea.flower_shop_service.infrastructure.common;

import edu.mirea.flower_shop_service.application.DeliveryService;
import edu.mirea.flower_shop_service.application.OrderService;
import edu.mirea.flower_shop_service.application.port.out.DeliveryPersistencePort;
import edu.mirea.flower_shop_service.application.port.out.DeliveryServicePort;
import edu.mirea.flower_shop_service.application.port.out.OrderPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppLayerBeanConfig {
    @Bean
    public DeliveryService createDeliveryUseCase(DeliveryPersistencePort deliveryPersistencePort,
                                                 OrderPersistencePort orderPersistencePort,
                                                 DeliveryServicePort deliveryServicePort) {
        return new DeliveryService(deliveryPersistencePort, orderPersistencePort, deliveryServicePort);
    }

    @Bean
    public OrderService createOrderUseCase(OrderPersistencePort orderPersistencePort) {
        return new OrderService(orderPersistencePort);
    }
}

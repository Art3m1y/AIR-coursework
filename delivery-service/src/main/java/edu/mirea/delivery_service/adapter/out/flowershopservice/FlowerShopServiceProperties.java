package edu.mirea.delivery_service.adapter.out.flowershopservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("integration.flower-shop-service")
@Getter
@Setter
@Component
public class FlowerShopServiceProperties {
    private String url;
    private String changeDeliveryStatus;
    private String apiKey;
}

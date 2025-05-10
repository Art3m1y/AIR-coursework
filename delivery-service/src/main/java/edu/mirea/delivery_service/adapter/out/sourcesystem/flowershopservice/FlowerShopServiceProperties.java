package edu.mirea.delivery_service.adapter.out.sourcesystem.flowershopservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("integration.flower-shop-service")
@Getter
@Setter
@Component
public class FlowerShopServiceProperties {
    private String host;
    private String apiKey;
}

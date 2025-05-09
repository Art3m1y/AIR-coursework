package edu.mirea.flower_shop_service.adapter.out.deliveryservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("integration.delivery-service")
@Getter
@Setter
@Component
public class DeliveryServiceProperties {
    private String url;
    private String createDeliveryPath;
    private String deleteDeliveryPath;
    private String apiKey;
}

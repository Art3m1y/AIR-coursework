package edu.mirea.delivery_service.adapter.out.flowershopservice;

import edu.mirea.delivery_service.adapter.out.IntegrationException;
import edu.mirea.delivery_service.adapter.out.IntegrationType;
import edu.mirea.delivery_service.adapter.out.flowershopservice.dto.ChangeDeliveryStatusRqDto;
import edu.mirea.delivery_service.application.port.out.ChangeDeliveryStatusInfo;
import edu.mirea.delivery_service.application.port.out.SourceServicePort;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@Slf4j
@AllArgsConstructor
public class FlowerShopServiceAdapter implements SourceServicePort {
    private static final MediaType REQUEST_MEDIA_TYPE = APPLICATION_JSON;
    private static final String API_KEY_HEADER = "X-API-KEY";
    private final RestClient restClient;
    private final FlowerShopServiceProperties flowerShopServiceProperties;

    @Override
    @Retry(name = IntegrationType.Names.FLOWER_SHOP_SERVICE, fallbackMethod = "handleChangeStatusIntegrationException")
    public void changeStatus(ChangeDeliveryStatusInfo info) {
        var apiKey = flowerShopServiceProperties.getApiKey();

        restClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(flowerShopServiceProperties.getUrl())
                        .build(info.getDeliveryId().getValue()))
                .contentType(REQUEST_MEDIA_TYPE)
                .body(new ChangeDeliveryStatusRqDto(info.getDeliveryStatus().name()))
                .header(API_KEY_HEADER, apiKey)
                .retrieve()
                .toBodilessEntity();
    }

    public void handleChangeStatusIntegrationException(ChangeDeliveryStatusInfo info, Exception e) {
        throw new IntegrationException("Ошибка при попытке интеграции с внешней системой %s: %s"
                .formatted(IntegrationType.FLOWER_SHOP_SERVICE, e.getMessage()), e);
    }
}

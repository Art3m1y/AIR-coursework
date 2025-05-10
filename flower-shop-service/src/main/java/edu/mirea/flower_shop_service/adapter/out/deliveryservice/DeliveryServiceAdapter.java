package edu.mirea.flower_shop_service.adapter.out.deliveryservice;

import edu.mirea.flower_shop_service.adapter.out.HttpUriBuilder;
import edu.mirea.flower_shop_service.adapter.out.IntegrationException;
import edu.mirea.flower_shop_service.adapter.out.IntegrationType;
import edu.mirea.flower_shop_service.adapter.out.deliveryservice.dto.CreateDeliveryRsDto;
import edu.mirea.flower_shop_service.application.port.out.DeliveryCancellationInfo;
import edu.mirea.flower_shop_service.application.port.out.DeliveryCreationInfo;
import edu.mirea.flower_shop_service.application.port.out.DeliveryCreationResult;
import edu.mirea.flower_shop_service.application.port.out.DeliveryServicePort;
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
public class DeliveryServiceAdapter implements DeliveryServicePort {
    private static final MediaType REQUEST_MEDIA_TYPE = APPLICATION_JSON;
    private static final String SOURCE_SYSTEM_HEADER = "SourceSystem";
    private final RestClient restClient;
    private final DeliveryServiceProperties deliveryServiceProperties;
    private final DeliveryMapper deliveryMapper;

    @Override
    @Retry(name = IntegrationType.Names.DELIVERY_SERVICE, fallbackMethod = "handleCreateDeliveryIntegrationException")
    public DeliveryCreationResult createDelivery(DeliveryCreationInfo info) {
        var requestDto = deliveryMapper.toRequestDto(info);
        var sourceSystem = deliveryServiceProperties.getSourceSystemName();

        var responseDto = restClient.post()
                .uri(uriBuilder -> HttpUriBuilder.from(deliveryServiceProperties.getHost())
                        .path(deliveryServiceProperties.getCreateDeliveryPath())
                        .build())
                .contentType(REQUEST_MEDIA_TYPE)
                .body(requestDto)
                .header(SOURCE_SYSTEM_HEADER, sourceSystem)
                .retrieve()
                .body(CreateDeliveryRsDto.class);

        log.info("Запрос в сервис-агрегатор доставки заказов с целью создания создания новой доставки выполнен успешно");

        return deliveryMapper.fromResponseDto(responseDto);
    }

    public DeliveryCreationResult handleCreateDeliveryException(DeliveryCreationInfo info, Exception e) {
        throw new IntegrationException("Ошибка при попытке интеграции с внешней системой %s: %s"
                .formatted(IntegrationType.DELIVERY_SERVICE, e.getMessage()), e);
    }

    @Override
    public void cancelDelivery(DeliveryCancellationInfo info) {
        var sourceSystem = deliveryServiceProperties.getSourceSystemName();

        restClient.delete()
                .uri(uriBuilder -> HttpUriBuilder.from(deliveryServiceProperties.getHost())
                        .path(deliveryServiceProperties.getDeleteDeliveryPath())
                        .build(info.getId()))
                .header(SOURCE_SYSTEM_HEADER, sourceSystem)
                .retrieve()
                .toBodilessEntity();

        log.info("Запрос в сервис-агрегатор доставки заказов с целью отмены имеющейся доставки выполнен успешно");
    }
}

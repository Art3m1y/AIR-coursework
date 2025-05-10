package edu.mirea.delivery_service.adapter.out.sourcesystem.specific;

import edu.mirea.delivery_service.adapter.out.IntegrationException;
import edu.mirea.delivery_service.adapter.out.IntegrationType;
import edu.mirea.delivery_service.application.port.out.ChangeDeliveryStatusInfo;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RequiredArgsConstructor
public abstract class BaseSpecificSourceSystemAdapter implements SpecificSourceSystemAdapter {
    private static final String CHANGE_STATUS_PATH = "api/v1/deliveries/{id}/change-status";
    private static final MediaType REQUEST_MEDIA_TYPE = APPLICATION_JSON;
    private static final String API_KEY_HEADER = "X-API-KEY";
    private final RestClient restClient;

    @Override
    @Retry(name = IntegrationType.Names.SOURCE_SYSTEM_SERVICE, fallbackMethod = "handleChangeStatusIntegrationException")
    public void changeStatus(ChangeDeliveryStatusInfo info) {
        restClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .host(getHost())
                        .path(CHANGE_STATUS_PATH)
                        .build(info.getDeliveryId().getValue()))
                .contentType(REQUEST_MEDIA_TYPE)
                .body(new ChangeDeliveryStatusRqDto(info.getDeliveryStatus().name()))
                .header(API_KEY_HEADER, getApiKey())
                .retrieve()
                .toBodilessEntity();
    }

    public boolean isApplicable(String sourceSystem) {
        return sourceSystem.equals(getSourceSystem().name());
    }

    public void handleChangeStatusIntegrationException(ChangeDeliveryStatusInfo info, Exception e) {
        throw new IntegrationException("Ошибка при попытке интеграции с внешней системой %s: %s"
                .formatted(info.getSourceSystem(), e.getMessage()), e);
    }

    protected abstract String getApiKey();

    protected abstract String getHost();

    protected abstract SourceSystem getSourceSystem();
}

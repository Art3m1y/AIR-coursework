package ru.Art3m1y.delivery_service.infrastructure.out.rest;

import io.micrometer.observation.ObservationRegistry;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.metrics.web.client.ObservationRestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.client.observation.DefaultClientRequestObservationConvention;
import org.springframework.web.client.RestClient;
import ru.Art3m1y.delivery_service.adapter.out.IntegrationType;
import ru.Art3m1y.delivery_service.adapter.out.deliveryservice.DeliveryServiceProperties;
import ru.Art3m1y.delivery_service.infrastructure.out.rest.interceptors.RestLoggingInterceptor;


@Configuration
@AllArgsConstructor
public class RestClientConfiguration {
    private final RestClientProperties restClientProperties;
    private final DeliveryServiceProperties deliveryServiceProperties;
    private final ObservationRegistry observationRegistry;

    @Bean
    public RestClient deliveryServiceRestClient() {
        var builder = RestClient.builder();
        var customizer = new ObservationRestClientCustomizer(
                observationRegistry,
                new DefaultClientRequestObservationConvention(IntegrationType.DELIVERY_SERVICE.getName())
        );
        customizer.customize(builder);
        return builder
                .baseUrl(deliveryServiceProperties.getUrl())
                .requestInterceptor(new RestLoggingInterceptor())
                .requestFactory(getCustomRequestFactory(IntegrationType.DELIVERY_SERVICE))
                .build();
    }

    private ClientHttpRequestFactory getCustomRequestFactory(IntegrationType integrationType) {
        var clientSpecification = restClientProperties.getSpecificationByIntegrationName(integrationType.getName());

        var requestFactory = new JdkClientHttpRequestFactory();
        requestFactory.setReadTimeout(clientSpecification.getReadTimeout());

        return requestFactory;
    }
}

package edu.mirea.delivery_service.infrastructure.out.rest;

import edu.mirea.delivery_service.adapter.out.IntegrationType;
import edu.mirea.delivery_service.infrastructure.out.rest.interceptors.RestLoggingInterceptor;
import io.micrometer.observation.ObservationRegistry;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.metrics.web.client.ObservationRestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.client.observation.DefaultClientRequestObservationConvention;
import org.springframework.web.client.RestClient;


@Configuration
@AllArgsConstructor
public class RestClientConfiguration {
    private final RestClientProperties restClientProperties;
    private final ObservationRegistry observationRegistry;

    @Bean
    public RestClient sourceSystemServiceRestClient() {
        var builder = RestClient.builder();
        var customizer = new ObservationRestClientCustomizer(
                observationRegistry,
                new DefaultClientRequestObservationConvention(IntegrationType.SOURCE_SYSTEM_SERVICE.getName())
        );
        customizer.customize(builder);
        return builder
                .requestInterceptor(new RestLoggingInterceptor())
                .requestFactory(getCustomRequestFactory(IntegrationType.SOURCE_SYSTEM_SERVICE))
                .build();
    }

    private ClientHttpRequestFactory getCustomRequestFactory(IntegrationType integrationType) {
        var clientSpecification = restClientProperties.getSpecificationByIntegrationName(integrationType.getName());

        var requestFactory = new JdkClientHttpRequestFactory();
        requestFactory.setReadTimeout(clientSpecification.getReadTimeout());

        return requestFactory;
    }
}

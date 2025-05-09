package ru.Art3m1y.delivery_service.infrastructure.out.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.NoSuchElementException;

@ConfigurationProperties("rest")
@Getter
@Setter
@Component
public class RestClientProperties {
    private Map<String, RestClientSpecification> specifications;

    public RestClientSpecification getSpecificationByIntegrationName(String integrationName) {
        var specification = specifications.get(integrationName);

        if (specification == null) {
            throw new NoSuchElementException("Спецификации с именем интеграции %s не найдено".formatted(integrationName));
        }

        return specification;
    }

    @Getter
    @Setter
    public static class RestClientSpecification {
        private Duration readTimeout;
    }
}

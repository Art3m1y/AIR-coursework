package edu.mirea.flower_shop_service.infrastructure.out.rest;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

public class HttpUriBuilder {
    private final UriComponentsBuilder builder;

    private HttpUriBuilder(String fullHost) {
        var host = extractHost(fullHost);
        var port = extractPort(fullHost);

        this.builder = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(host);

        port.ifPresent(builder::port);
    }

    public static HttpUriBuilder from(String host) {
        return new HttpUriBuilder(host);
    }

    public HttpUriBuilder path(String pathTemplate) {
        builder.path(pathTemplate);
        return this;
    }

    public HttpUriBuilder queryParam(String name, Object value) {
        builder.queryParam(name, value);
        return this;
    }

    public URI build(Object... uriVariables) {
        return builder
                .buildAndExpand(uriVariables)
                .toUri();
    }

    private String extractHost(String hostWithPort) {
        int colonIndex = hostWithPort.indexOf(':');
        if (colonIndex > -1 && colonIndex != hostWithPort.length() - 1) {
            return hostWithPort.substring(0, colonIndex);
        }
        return hostWithPort;
    }

    private Optional<Integer> extractPort(String hostWithPort) {
        int colonIndex = hostWithPort.indexOf(':');
        if (colonIndex > -1 && colonIndex < hostWithPort.length() - 1) {
            String portPart = hostWithPort.substring(colonIndex + 1);
            try {
                return Optional.of(Integer.parseInt(portPart));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Некорректный порт в переданном хосте: %s".formatted(portPart));
            }
        }
        return Optional.empty();
    }
}
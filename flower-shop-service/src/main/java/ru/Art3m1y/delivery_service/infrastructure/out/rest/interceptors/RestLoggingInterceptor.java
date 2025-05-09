package ru.Art3m1y.delivery_service.infrastructure.out.rest.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class RestLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution
    ) throws IOException {
        logRequest(request, body);
        var response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) {
        var headers = formatHeaders(request.getHeaders());
        var payload = body.length > 0
                ? "; body=" + new String(body, StandardCharsets.UTF_8)
                : "";
        log.info("Исходящий HTTP-запрос → {} {}; headers={} {}",
                request.getMethod(), request.getURI(), headers, payload);
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        var headers = formatHeaders(response.getHeaders());
        var body = new BufferedReader(
                new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))
                .lines().collect(Collectors.joining());
        var payload = !body.isBlank() ? "; body=" + body : "";
        log.info("Ответ на исходящий HTTP-запрос ← {} {}; headers={}",
                response.getStatusCode(), payload, headers);
    }

    private String formatHeaders(HttpHeaders headers) {
        return headers.entrySet().stream()
                .map(e -> e.getKey() + "=" + String.join(",", e.getValue()))
                .collect(Collectors.joining(","));
    }
}

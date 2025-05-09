package edu.mirea.flower_shop_service.infrastructure.in.rest.filter.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain
    ) throws ServletException, IOException {
        var requestWrapper  = new ContentCachingRequestWrapper(req);
        var responseWrapper = new ContentCachingResponseWrapper(res);

        try {
            chain.doFilter(requestWrapper, responseWrapper);
        } finally {

        }

        logRequest(requestWrapper);
        logResponse(responseWrapper);

        responseWrapper.copyBodyToResponse();
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        String headers = Collections.list(request.getHeaderNames()).stream()
                .map(name -> name + "=" + Collections.list(request.getHeaders(name)))
                .collect(Collectors.joining(", "));
        String body = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
        String payload = body.isBlank() ? "" : "; body=" + body;

        log.info("Входящий HTTP-запрос → {} {}; headers=[{}]{}",
                request.getMethod(), request.getRequestURI(), headers, payload);
    }

    private void logResponse(ContentCachingResponseWrapper response) {
        String headers = response.getHeaderNames().stream()
                .map(name -> name + "=" + response.getHeaders(name))
                .collect(Collectors.joining(", "));
        String body = new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);
        String payload = body.isBlank() ? "" : "; body=" + body;

        log.info("Ответ на входящий HTTP-запрос ← {}; headers=[{}]{}",
                response.getStatus(), headers, payload);
    }
}
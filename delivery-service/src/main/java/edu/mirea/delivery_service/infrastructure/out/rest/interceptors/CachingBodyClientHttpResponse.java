package edu.mirea.delivery_service.infrastructure.out.rest.interceptors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CachingBodyClientHttpResponse implements ClientHttpResponse {
    private final ClientHttpResponse delegate;
    private byte[] body;

    public CachingBodyClientHttpResponse(ClientHttpResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    public HttpStatusCode getStatusCode() throws IOException {
        return delegate.getStatusCode();
    }

    @Override
    public String getStatusText() throws IOException {
        return delegate.getStatusText();
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public InputStream getBody() throws IOException {
        if (body == null) {
            body = StreamUtils.copyToByteArray(delegate.getBody());
        }

        return new ByteArrayInputStream(body);
    }

    @Override
    public HttpHeaders getHeaders() {
        return delegate.getHeaders();
    }
}

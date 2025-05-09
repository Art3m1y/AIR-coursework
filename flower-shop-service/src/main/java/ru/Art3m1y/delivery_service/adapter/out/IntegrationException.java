package ru.Art3m1y.delivery_service.adapter.out;

public class IntegrationException extends RuntimeException {
    public IntegrationException(String message, Exception e) {
        super(message, e);
    }
}

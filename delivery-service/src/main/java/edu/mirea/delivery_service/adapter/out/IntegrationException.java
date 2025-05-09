package edu.mirea.delivery_service.adapter.out;

public class IntegrationException extends RuntimeException {
    public IntegrationException(String message, Exception e) {
        super(message, e);
    }
}

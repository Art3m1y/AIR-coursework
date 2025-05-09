package ru.Art3m1y.delivery_service.infrastructure.in.rest.errorhandling;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponseDto {
    private final String code;
    private final String message;
}

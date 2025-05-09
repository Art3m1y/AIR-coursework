package ru.Art3m1y.delivery_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Recipient {
    private String name;
    private String phone;
}

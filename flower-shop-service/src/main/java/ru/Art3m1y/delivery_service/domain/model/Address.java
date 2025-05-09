package ru.Art3m1y.delivery_service.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class Address {
    private final String city;
    private final String street;
    private final String house;
    private final String apartment;
    private final String comment;

    public Address(String city, String street, String house, String apartment, String comment) {
        this.city = Objects.requireNonNull(city, "City cannot be null");
        this.street = Objects.requireNonNull(street, "Street cannot be null");
        this.house = Objects.requireNonNull(house, "House cannot be null");
        this.apartment = Objects.requireNonNull(apartment, "Apartment cannot be null");
        this.comment = comment;
    }
}

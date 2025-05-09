package ru.Art3m1y.delivery_service.adapter.out.deliveryservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
@Getter
public class CreateDeliveryRsDto {
    private final Recipient recipient;
    private final Address address;
    private final List<Item> items;
    private final String id;
    private final String status;

    @Builder
    @Jacksonized
    public static class Recipient {
        private final String name;
        private final String phone;
    }

    @Builder
    @Jacksonized
    public static class Address {
        private final String city;
        private final String street;
        private final String house;
        private final String apartment;
        private final String comment;
    }

    @Builder
    @Jacksonized
    public static class Item {
        private final String code;
        private final int quantity;
    }
}

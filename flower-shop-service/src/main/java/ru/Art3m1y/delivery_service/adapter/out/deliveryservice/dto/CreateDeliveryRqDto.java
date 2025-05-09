package ru.Art3m1y.delivery_service.adapter.out.deliveryservice.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
public class CreateDeliveryRqDto {
    private Recipient recipient;
    private Address address;
    private List<Item> items;

    @Builder
    @Jacksonized
    public static class Recipient {
        private String name;
        private String phone;
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

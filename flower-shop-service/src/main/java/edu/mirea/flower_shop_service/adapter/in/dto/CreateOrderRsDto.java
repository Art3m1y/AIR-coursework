package edu.mirea.flower_shop_service.adapter.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
@Getter
public class CreateOrderRsDto {
    private final String id;
    private final String status;
    private final Recipient recipient;
    private final Address address;
    private final List<Item> items;

    @Builder
    @Jacksonized
    @Getter
    public static class Recipient {
        private final String name;
        private final String phone;
    }

    @Builder
    @Jacksonized
    @Getter
    public static class Address {
        private final String city;
        private final String street;
        private final String house;
        private final String apartment;
        private final String comment;
    }

    @Builder
    @Jacksonized
    @Getter
    public static class Item {
        private final String code;
        private final int quantity;
    }
}

package edu.mirea.delivery_service.adapter.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
@Getter
public class CreateDeliveryRsDto {
    private Recipient recipient;
    private Address address;
    private List<Item> items;
    private String id;
    private String status;

    @Builder
    @Jacksonized
    @Getter
    public static class Recipient {
        private String name;
        private String phone;
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

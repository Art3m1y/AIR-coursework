package edu.mirea.flower_shop_service.adapter.out.deliveryservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
@Getter
public class CreateDeliveryRqDto {
    private Recipient recipient;
    private Address address;
    private List<Item> items;

    @Builder
    @Jacksonized
    @Getter
    public static class Recipient {
        private String name;
        private String phone;
    }

    @Builder
    @Getter
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
    @Getter
    public static class Item {
        private final String code;
        private final int quantity;
    }
}

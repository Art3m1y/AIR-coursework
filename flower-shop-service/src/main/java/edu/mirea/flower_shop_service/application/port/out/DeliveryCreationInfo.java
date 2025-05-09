package edu.mirea.flower_shop_service.application.port.out;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DeliveryCreationInfo {
    private Recipient recipient;
    private Address address;
    private List<Item> items;

    @Getter
    @Builder
    public static class Recipient {
        private String name;
        private String phone;
    }

    @Getter
    @Builder
    public static class Address {
        private final String city;
        private final String street;
        private final String house;
        private final String apartment;
        private final String comment;
    }

    @Getter
    @Builder
    public static class Item {
        private final String code;
        private final int quantity;
    }
}

package edu.mirea.flower_shop_service.adapter.in.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
@Getter
public class CreateOrderRqDto {
    @NotNull
    @Valid
    private Recipient recipient;
    @NotNull
    @Valid
    private Address address;
    @NotNull
    @Size(min = 1, max = 100)
    @Valid
    private List<Item> items;

    @Builder
    @Jacksonized
    @Getter
    public static class Recipient {
        @NotNull
        @Size(min = 5, max = 100)
        private String name;
        @NotNull
        @Pattern(regexp = "^(\\+7|8)\\d{10}$")
        private String phone;
    }

    @Builder
    @Jacksonized
    @Getter
    public static class Address {
        @NotNull
        @Size(min = 5, max = 100)
        private final String city;
        @NotNull
        @Size(min = 5, max = 100)
        private final String street;
        @NotNull
        @Size(max = 100)
        private final String house;
        @NotNull
        @Size(max = 100)
        private final String apartment;
        @Size(max = 200)
        private final String comment;
    }

    @Builder
    @Jacksonized
    @Getter
    public static class Item {
        @NotNull
        @Pattern(regexp = "^[A-Z]{4}-\\d{4}$")
        private final String code;
        @Positive
        @Max(100)
        private final int quantity;
    }
}

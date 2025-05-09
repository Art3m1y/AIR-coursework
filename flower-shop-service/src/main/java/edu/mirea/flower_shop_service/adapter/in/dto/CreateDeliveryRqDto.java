package edu.mirea.flower_shop_service.adapter.in.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CreateDeliveryRqDto {
    @NotNull
    private UUID orderId;
}

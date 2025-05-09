package edu.mirea.flower_shop_service.adapter.in.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChangeStatusRqDto {
    @NotNull
    private String status;
}

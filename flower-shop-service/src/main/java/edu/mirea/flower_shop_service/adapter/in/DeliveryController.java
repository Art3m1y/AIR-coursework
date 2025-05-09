package edu.mirea.flower_shop_service.adapter.in;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import edu.mirea.flower_shop_serviceadapter.in.dto.ChangeStatusRqDto;
import edu.mirea.flower_shop_service.application.port.in.ChangeDeliveryStatusCommand;
import edu.mirea.flower_shop_service.application.port.in.ChangeDeliveryStatusUseCase;
import edu.mirea.flower_shop_service.domain.model.DeliveryId;

@RestController
@RequestMapping("/api/v1/deliveries")
@Slf4j
@RequiredArgsConstructor
public class DeliveryController {
    private final ChangeDeliveryStatusUseCase changeDeliveryStatusUseCase;

    @PostMapping("/{deliveryId}/change-status")
    public void changeDeliveryStatus(@PathVariable("deliveryId") String id,
                                     @RequestBody ChangeStatusRqDto requestDto) {
        changeDeliveryStatusUseCase.changeStatus(
                ChangeDeliveryStatusCommand.builder()
                        .id(DeliveryId.fromString(id))
                        .status(requestDto.getStatus())
                        .build()
        );
    }
}

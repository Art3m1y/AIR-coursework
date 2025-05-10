package edu.mirea.delivery_service.adapter.in;

import edu.mirea.delivery_service.application.port.in.CancelDeliveryCommand;
import edu.mirea.delivery_service.application.port.in.CancelDeliveryUseCase;
import edu.mirea.delivery_service.application.port.in.CreateDeliveryCommand;
import edu.mirea.delivery_service.application.port.in.CreateDeliveryUseCase;
import edu.mirea.delivery_service.application.port.in.PromoteDeliveryStatusCommand;
import edu.mirea.delivery_service.application.port.in.PromoteDeliveryStatusUseCase;
import edu.mirea.delivery_service.domain.model.DeliveryId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import edu.mirea.delivery_service.adapter.in.dto.CreateDeliveryRqDto;
import edu.mirea.delivery_service.adapter.in.dto.CreateDeliveryRsDto;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/deliveries")
@Slf4j
@RequiredArgsConstructor
public class DeliveryController {
    private final CreateDeliveryUseCase createDeliveryUseCase;
    private final CancelDeliveryUseCase cancelDeliveryUseCase;
    private final PromoteDeliveryStatusUseCase promoteDeliveryStatusUseCase;
    private final DeliveryMapper deliveryMapper;

    @PostMapping
    public CreateDeliveryRsDto createDelivery(@RequestBody CreateDeliveryRqDto requestDto) {
        var result = createDeliveryUseCase.createDelivery(new CreateDeliveryCommand(deliveryMapper.toDelivery(requestDto)));
        return deliveryMapper.toResponseDto(result);
    }

    @DeleteMapping("{deliveryId}/cancel")
    public void cancelDelivery(@PathVariable UUID deliveryId) {
        cancelDeliveryUseCase.cancelDelivery(
                new CancelDeliveryCommand(new DeliveryId(deliveryId))
        );
    }

    @PostMapping("/{deliveryId}/promote-status")
    public void promoteDeliveryStatus(@PathVariable UUID deliveryId) {
        promoteDeliveryStatusUseCase.promoteDeliveryStatus(
                new PromoteDeliveryStatusCommand(new DeliveryId(deliveryId))
        );
    }
}

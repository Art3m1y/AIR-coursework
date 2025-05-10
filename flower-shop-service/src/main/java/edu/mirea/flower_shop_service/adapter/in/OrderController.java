package edu.mirea.flower_shop_service.adapter.in;

import edu.mirea.flower_shop_service.adapter.in.dto.CreateOrderRqDto;
import edu.mirea.flower_shop_service.adapter.in.dto.CreateOrderRsDto;
import edu.mirea.flower_shop_service.application.port.in.CancelDeliveryCommand;
import edu.mirea.flower_shop_service.application.port.in.CancelDeliveryUseCase;
import edu.mirea.flower_shop_service.application.port.in.CreateDeliveryCommand;
import edu.mirea.flower_shop_service.application.port.in.CreateDeliveryUseCase;
import edu.mirea.flower_shop_service.application.port.in.CreateOrderCommand;
import edu.mirea.flower_shop_service.application.port.in.CreateOrderUseCase;
import edu.mirea.flower_shop_service.domain.model.OrderId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final CreateDeliveryUseCase createDeliveryUseCase;
    private final CancelDeliveryUseCase cancelDeliveryUseCase;
    private final OrderMapper orderMapper;

    @PostMapping
    public CreateOrderRsDto createOrder(@RequestBody @Validated CreateOrderRqDto requestDto) {
        var result = createOrderUseCase.createOrder(new CreateOrderCommand(orderMapper.toOrder(requestDto)));
        return orderMapper.toResponseDto(result);
    }

    @PostMapping("/{orderId}/create-delivery")
    public void createDeliveryFromOrder(@PathVariable UUID orderId) {
        createDeliveryUseCase.createDeliveryFromOrder(CreateDeliveryCommand.builder().id(new OrderId(orderId)).build());
    }

    @DeleteMapping("/{orderId}/cancel-delivery")
    public void cancelDeliveryByOrder(@PathVariable UUID orderId) {
        cancelDeliveryUseCase.cancelDeliveryFromOrder(CancelDeliveryCommand.builder().id(new OrderId(orderId)).build());
    }
}
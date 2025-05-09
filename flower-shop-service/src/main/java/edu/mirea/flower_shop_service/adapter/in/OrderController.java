package edu.mirea.flower_shop_service.adapter.in;

import edu.mirea.flower_shop_service.adapter.in.dto.CreateOrderRqDto;
import edu.mirea.flower_shop_service.application.port.in.CancelDeliveryCommand;
import edu.mirea.flower_shop_service.application.port.in.CancelDeliveryUseCase;
import edu.mirea.flower_shop_service.application.port.in.CreateDeliveryCommand;
import edu.mirea.flower_shop_service.application.port.in.CreateDeliveryUseCase;
import edu.mirea.flower_shop_service.application.port.in.CreateOrderCommand;
import edu.mirea.flower_shop_service.application.port.in.CreateOrderUseCase;
import edu.mirea.flower_shop_service.domain.model.Address;
import edu.mirea.flower_shop_service.domain.model.Item;
import edu.mirea.flower_shop_service.domain.model.Order;
import edu.mirea.flower_shop_service.domain.model.OrderId;
import edu.mirea.flower_shop_service.domain.model.Recipient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final CreateDeliveryUseCase createDeliveryUseCase;
    private final CancelDeliveryUseCase cancelDeliveryUseCase;

    @PostMapping
    public void createOrder(@RequestBody @Validated CreateOrderRqDto requestDto) {
        createOrderUseCase.createOrder(mapToCreateOrderCommand(requestDto));
    }

    @PostMapping("/{orderId}/create-delivery")
    public void createDeliveryFromOrder(@PathVariable UUID orderId) {
        createDeliveryUseCase.createDeliveryFromOrder(CreateDeliveryCommand.builder().id(new OrderId(orderId)).build());
    }

    @DeleteMapping("/{orderId}/cancel-delivery")
    public void cancelDeliveryByOrder(@PathVariable UUID orderId) {
        cancelDeliveryUseCase.cancelDeliveryFromOrder(CancelDeliveryCommand.builder().id(new OrderId(orderId)).build());
    }

    public CreateOrderCommand mapToCreateOrderCommand(CreateOrderRqDto requestDto) {
        return new CreateOrderCommand(new Order(null, mapItems(requestDto.getItems()), mapRecipient(requestDto.getRecipient()), mapAddress(requestDto.getAddress()), null));
    }

    private Recipient mapRecipient(CreateOrderRqDto.Recipient source) {
        return new Recipient(source.getName(), source.getPhone());
    }

    private Address mapAddress(CreateOrderRqDto.Address source) {
        return Address.builder().city(source.getCity()).street(source.getStreet()).house(source.getHouse()).apartment(source.getApartment()).comment(source.getComment()).build();
    }

    private List<Item> mapItems(List<CreateOrderRqDto.Item> items) {
        return items.stream().map(item -> new Item(item.getCode(), item.getQuantity())).toList();
    }
}
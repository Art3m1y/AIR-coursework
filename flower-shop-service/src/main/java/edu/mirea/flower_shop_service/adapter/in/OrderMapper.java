package edu.mirea.flower_shop_service.adapter.in;

import edu.mirea.flower_shop_service.adapter.in.dto.CreateOrderRqDto;
import edu.mirea.flower_shop_service.adapter.in.dto.CreateOrderRsDto;
import edu.mirea.flower_shop_service.domain.model.Item;
import edu.mirea.flower_shop_service.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "items", source = "items", qualifiedByName = "mapDtoToItems")
    Order toOrder(CreateOrderRqDto requestDto);
    @Mapping(target = "id", expression = "java(order.getId().getValue().toString())")
    @Mapping(target = "items", source = "items", qualifiedByName = "mapItemsToDto")
    CreateOrderRsDto toResponseDto(Order order);

    @Named("mapItemsToDto")
    default List<CreateOrderRsDto.Item> mapItemsToDto(List<Item> items) {
        return items.stream()
                .map(item -> CreateOrderRsDto.Item.builder()
                        .code(item.getCode().getValue().toString())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    @Named("mapDtoToItems")
    default List<Item> mapDtoToItems(List<CreateOrderRqDto.Item> items) {
        return items.stream()
                .map(item -> new Item(item.getCode(), item.getQuantity()))
                .collect(Collectors.toList());
    }
}

package edu.mirea.delivery_service.adapter.in;

import edu.mirea.delivery_service.adapter.in.dto.CreateDeliveryRqDto;
import edu.mirea.delivery_service.adapter.in.dto.CreateDeliveryRsDto;
import edu.mirea.delivery_service.domain.model.Delivery;
import edu.mirea.delivery_service.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    @Mapping(target = "items", source = "items", qualifiedByName = "mapDtoToItems")
    Delivery toDelivery(CreateDeliveryRqDto createDeliveryRqDto);
    @Mapping(target = "id", expression = "java(delivery.getId().getValue().toString())")
    @Mapping(target = "items", source = "items", qualifiedByName = "mapItemsToDto")
    CreateDeliveryRsDto toResponseDto(Delivery delivery);

    @Named("mapItemsToDto")
    default List<CreateDeliveryRsDto.Item> mapItemsToDto(List<Item> items) {
        return items.stream()
                .map(item -> CreateDeliveryRsDto.Item.builder()
                        .code(item.getCode().getValue().toString())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    @Named("mapDtoToItems")
    default List<Item> mapDtoToItems(List<CreateDeliveryRqDto.Item> items) {
        return items.stream()
                .map(item -> new Item(item.getCode(), item.getQuantity()))
                .collect(Collectors.toList());
    }
}

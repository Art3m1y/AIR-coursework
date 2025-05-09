package ru.Art3m1y.delivery_service.adapter.out.deliveryservice;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.Art3m1y.delivery_service.adapter.out.deliveryservice.dto.CreateDeliveryRqDto;
import ru.Art3m1y.delivery_service.adapter.out.deliveryservice.dto.CreateDeliveryRsDto;
import ru.Art3m1y.delivery_service.application.port.out.DeliveryCreationInfo;
import ru.Art3m1y.delivery_service.application.port.out.DeliveryCreationResult;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    CreateDeliveryRqDto toRequestDto(DeliveryCreationInfo info);
    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "externalStatus", source = "status")
    DeliveryCreationResult fromResponseDto(CreateDeliveryRsDto dto);
}

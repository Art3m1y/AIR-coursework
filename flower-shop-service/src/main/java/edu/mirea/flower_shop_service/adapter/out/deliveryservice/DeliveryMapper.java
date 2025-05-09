package edu.mirea.flower_shop_service.adapter.out.deliveryservice;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import edu.mirea.flower_shop_service.adapter.out.deliveryservice.dto.CreateDeliveryRqDto;
import edu.mirea.flower_shop_service.adapter.out.deliveryservice.dto.CreateDeliveryRsDto;
import edu.mirea.flower_shop_service.application.port.out.DeliveryCreationInfo;
import edu.mirea.flower_shop_service.application.port.out.DeliveryCreationResult;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    CreateDeliveryRqDto toRequestDto(DeliveryCreationInfo info);
    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "externalStatus", source = "status")
    DeliveryCreationResult fromResponseDto(CreateDeliveryRsDto dto);
}

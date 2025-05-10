package edu.mirea.flower_shop_service.application;

import edu.mirea.flower_shop_service.application.port.in.CancelDeliveryCommand;
import edu.mirea.flower_shop_service.application.port.in.CancelDeliveryUseCase;
import edu.mirea.flower_shop_service.application.port.in.ChangeDeliveryStatusCommand;
import edu.mirea.flower_shop_service.application.port.in.ChangeDeliveryStatusUseCase;
import edu.mirea.flower_shop_service.application.port.in.CreateDeliveryCommand;
import edu.mirea.flower_shop_service.application.port.in.CreateDeliveryUseCase;
import edu.mirea.flower_shop_service.application.port.out.DeliveryCancellationInfo;
import edu.mirea.flower_shop_service.application.port.out.DeliveryCreationInfo;
import edu.mirea.flower_shop_service.application.port.out.DeliveryPersistencePort;
import edu.mirea.flower_shop_service.application.port.out.DeliveryServicePort;
import edu.mirea.flower_shop_service.application.port.out.OrderPersistencePort;
import edu.mirea.flower_shop_service.domain.enumeration.InnerDeliveryStatus;
import edu.mirea.flower_shop_service.domain.enumeration.OrderStatus;
import edu.mirea.flower_shop_service.domain.exception.ObjectNotFoundException;
import edu.mirea.flower_shop_service.domain.model.Delivery;
import edu.mirea.flower_shop_service.domain.model.DeliveryId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
public class DeliveryService implements ChangeDeliveryStatusUseCase, CreateDeliveryUseCase, CancelDeliveryUseCase {
    private final DeliveryPersistencePort deliveryPersistencePort;
    private final OrderPersistencePort orderPersistencePort;
    private final DeliveryServicePort deliveryServicePort;

    @Override
    public void changeStatus(ChangeDeliveryStatusCommand command) {
        var deliveryOpt = deliveryPersistencePort.findDelivery(command.getId().getValue().toString());

        if (deliveryOpt.isEmpty()) {
            throw new ObjectNotFoundException("Не найдена доставка с идентификатором %s".formatted(command.getId().getValue()));
        }

        var delivery = deliveryOpt.get();
        delivery.setExternalStatus(command.getStatus());

        deliveryPersistencePort.updateDeliveryState(delivery);

        log.info("Статус доставки изменен на {}", command.getStatus());
    }

    @Override
    public void createDeliveryFromOrder(CreateDeliveryCommand command) {
        var orderOpt = orderPersistencePort.findOrder(command.getId());

        if (orderOpt.isEmpty()) {
            throw new ObjectNotFoundException("Не найден заказ с идентификатором %s".formatted(command.getId().getValue()));
        }

        var order = orderOpt.get();
        var delivery = new Delivery(
                order.getRecipient(),
                order.getAddress(),
                order.getItems()
        );

        var deliveryCreationInfo = mapToDeliveryCreationInfo(delivery);
        var deliveryCreationResult = deliveryServicePort.createDelivery(deliveryCreationInfo);
        
        delivery.markAsSuccess(deliveryCreationResult.getExternalId(), deliveryCreationResult.getExternalStatus());
        
        deliveryPersistencePort.addDelivery(delivery);

        order.setDeliveryId(DeliveryId.fromString(delivery.getExternalId()));
        order.setStatus(OrderStatus.DELIVERING);
        orderPersistencePort.updateOrderState(order);

        log.info("Доставка по переданному заказу успешно создана");
    }

    @Override
    public void cancelDeliveryFromOrder(CancelDeliveryCommand command) {
        var orderOpt = orderPersistencePort.findOrder(command.getId());
        if (orderOpt.isEmpty()) {
            throw new ObjectNotFoundException("Не найден заказ с идентификатором %s".formatted(command.getId().getValue()));
        }
        var order = orderOpt.get();

        if (!order.isInDelivery()) {
            throw new IllegalStateException("У заказа с идентификатором %s пока нет доставки".formatted(command.getId().getValue()));
        }

        var deliveryOpt = deliveryPersistencePort.findDelivery(order.getDeliveryId().getValue().toString());
        var delivery = deliveryOpt.get();

        var deliveryCancellationInfo = DeliveryCancellationInfo.builder()
                .id(delivery.getExternalId())
                .build();
        deliveryServicePort.cancelDelivery(deliveryCancellationInfo);
        delivery.setInnerStatus(InnerDeliveryStatus.CANCELLED);

        log.info("Доставка для переданного заказа успешного отменена");
    }

    private DeliveryCreationInfo mapToDeliveryCreationInfo(Delivery delivery) {
        return DeliveryCreationInfo.builder()
                .recipient(DeliveryCreationInfo.Recipient.builder()
                        .phone(delivery.getRecipient().getPhone())
                        .name(delivery.getRecipient().getName())
                        .build())
                .address(DeliveryCreationInfo.Address.builder()
                        .city(delivery.getAddress().getCity())
                        .street(delivery.getAddress().getStreet())
                        .house(delivery.getAddress().getHouse())
                        .apartment(delivery.getAddress().getApartment())
                        .comment(delivery.getAddress().getComment())
                        .build())
                .items(delivery
                        .getItems()
                        .stream()
                        .map(sourceItem -> DeliveryCreationInfo.Item.builder()
                                .code(sourceItem.getCode().getValue())
                                .quantity(sourceItem.getQuantity())
                                .build())
                        .toList())
                .build();
    }
}

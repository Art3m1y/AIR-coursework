package edu.mirea.delivery_service.application;

import edu.mirea.delivery_service.application.port.in.CancelDeliveryCommand;
import edu.mirea.delivery_service.application.port.in.CancelDeliveryUseCase;
import edu.mirea.delivery_service.application.port.in.CreateDeliveryCommand;
import edu.mirea.delivery_service.application.port.in.CreateDeliveryUseCase;
import edu.mirea.delivery_service.application.port.in.PromoteDeliveryStatusCommand;
import edu.mirea.delivery_service.application.port.in.PromoteDeliveryStatusUseCase;
import edu.mirea.delivery_service.application.port.out.ChangeDeliveryStatusInfo;
import edu.mirea.delivery_service.application.port.out.DeliveryPersistencePort;
import edu.mirea.delivery_service.application.port.out.SourceServicePort;
import edu.mirea.delivery_service.domain.enumeration.DeliveryStatus;
import edu.mirea.delivery_service.domain.exception.ObjectNotFoundException;
import edu.mirea.delivery_service.domain.model.Delivery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DeliveryService implements CreateDeliveryUseCase, CancelDeliveryUseCase, PromoteDeliveryStatusUseCase {
    private final DeliveryPersistencePort deliveryPersistencePort;
    private final SourceServicePort sourceServicePort;

    @Override
    public void cancelDelivery(CancelDeliveryCommand command) {
        var deliveryOpt = deliveryPersistencePort.findDelivery(command.getId());

        if (deliveryOpt.isEmpty()) {
            throw new ObjectNotFoundException("Не найдена доставка с идентификатором %s".formatted(command.getId().getValue()));
        }

        var delivery = deliveryOpt.get();
        delivery.cancelDelivery();

        log.info("Доставка с идентификатором {} отменена", delivery.getId().getValue());
    }

    @Override
    public Delivery createDelivery(CreateDeliveryCommand command) {
        var delivery = command.getDelivery();

        delivery.setStatus(DeliveryStatus.NEW);

        var deliveryId = deliveryPersistencePort.addDelivery(delivery);
        delivery.setId(deliveryId);

        log.info("Доставка для внешнего заказа сохранена");
        return delivery;
    }

    @Override
    public void promoteDeliveryStatus(PromoteDeliveryStatusCommand command) {
        var deliveryOpt = deliveryPersistencePort.findDelivery(command.getId());

        if (deliveryOpt.isEmpty()) {
            throw new ObjectNotFoundException("Не найдена доставка".formatted(command.getId().getValue()));
        }

        var delivery = deliveryOpt.get();
        delivery.moveDeliveryStatus();

        var changeDeliveryStatusInfo = new ChangeDeliveryStatusInfo(delivery.getId(), delivery.getStatus(), delivery.getSourceSystem());
        sourceServicePort.changeStatus(changeDeliveryStatusInfo);

        deliveryPersistencePort.updateDeliveryState(delivery);
    }
}

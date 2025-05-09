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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
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
        delivery.setStatus(DeliveryStatus.CANCELLED);
    }

    @Override
    public void createDelivery(CreateDeliveryCommand command) {
        var delivery = command.getDelivery();
        delivery.setStatus(DeliveryStatus.NEW);

        deliveryPersistencePort.addDelivery(delivery);

        log.info("Доставка для внешнего заказа {} сохранена");
    }

    @Override
    public void promoteDeliveryStatus(PromoteDeliveryStatusCommand command) {
        var deliveryOpt = deliveryPersistencePort.findDelivery(command.getId());

        if (deliveryOpt.isEmpty()) {
            throw new ObjectNotFoundException("Не найдена доставка с идентификатором %s".formatted(command.getId().getValue()));
        }

        var delivery = deliveryOpt.get();
        var nextStatusOpt = DeliveryStatus.findNextFor(delivery.getStatus());
        if (nextStatusOpt.isEmpty()) {
            throw new IllegalStateException("Доставка находится в конечном состоянии, невозможно перевести статус");
        }
        delivery.setStatus(nextStatusOpt.get());

        var changeDeliveryStatusInfo = new ChangeDeliveryStatusInfo(delivery.getId(), delivery.getStatus());
        sourceServicePort.changeStatus(changeDeliveryStatusInfo);

        deliveryPersistencePort.updateDeliveryState(delivery);
    }
}

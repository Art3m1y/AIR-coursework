package edu.mirea.delivery_service.adapter.out.sourcesystem;

import edu.mirea.delivery_service.adapter.out.sourcesystem.specific.SpecificSourceSystemAdapter;
import edu.mirea.delivery_service.application.port.out.ChangeDeliveryStatusInfo;
import edu.mirea.delivery_service.application.port.out.SourceServicePort;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@AllArgsConstructor
@Component
public class CompositeSourceSystemAdapter implements SourceServicePort {
    private final List<SpecificSourceSystemAdapter> specificSourceSystemAdapters;

    @Override
    public void changeStatus(ChangeDeliveryStatusInfo info) {
        var specificAdapter = findSpecificSourceSystemAdapter(info.getSourceSystem());
        specificAdapter.changeStatus(info);
    }

    private SpecificSourceSystemAdapter findSpecificSourceSystemAdapter(String sourceSystem) {
        return specificSourceSystemAdapters.stream()
                .filter(specificAdapter -> specificAdapter.isApplicable(sourceSystem))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Для переданного кода системы-источника (%s) не найдено адаптера"
                        .formatted(sourceSystem)));
    }
}

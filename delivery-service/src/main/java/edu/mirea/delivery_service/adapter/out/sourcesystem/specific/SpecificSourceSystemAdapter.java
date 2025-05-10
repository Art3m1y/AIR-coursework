package edu.mirea.delivery_service.adapter.out.sourcesystem.specific;

import edu.mirea.delivery_service.application.port.out.SourceServicePort;

public interface SpecificSourceSystemAdapter extends SourceServicePort {
    boolean isApplicable(String sourceSystem);
}

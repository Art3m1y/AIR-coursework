package edu.mirea.delivery_service.adapter.out.sourcesystem.specific.systems.flowershopservice;

import edu.mirea.delivery_service.adapter.out.sourcesystem.specific.BaseSpecificSourceSystemAdapter;
import edu.mirea.delivery_service.adapter.out.sourcesystem.specific.SourceSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
public class FlowerShopServiceAdapter extends BaseSpecificSourceSystemAdapter {
    private final FlowerShopServiceProperties flowerShopServiceProperties;

    public FlowerShopServiceAdapter(FlowerShopServiceProperties flowerShopServiceProperties,
                                    RestClient restClient) {
        super(restClient);
        this.flowerShopServiceProperties = flowerShopServiceProperties;
    }

    @Override
    protected String getApiKey() {
        return flowerShopServiceProperties.getApiKey();
    }

    @Override
    protected String getHost() {
        return flowerShopServiceProperties.getHost();
    }

    @Override
    public SourceSystem getSourceSystem() {
        return SourceSystem.FLOWER_SHOP_SERVICE;
    }
}

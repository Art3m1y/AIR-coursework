package edu.mirea.flower_shop_service.domain.model;

import lombok.Getter;
import lombok.Setter;
import edu.mirea.flower_shop_service.domain.enumeration.InnerDeliveryStatus;

import java.util.List;

@Getter
public class Delivery {
    private Recipient recipient;
    private Address address;
    private List<Item> items;
    @Setter
    private InnerDeliveryStatus innerStatus;
    private String externalId;
    @Setter
    private String externalStatus;

    public Delivery(Recipient recipient, Address address, List<Item> items) {
        this.recipient = recipient;
        this.address = address;
        this.items = items;
        this.innerStatus = InnerDeliveryStatus.DRAFT;
    }

    public void markAsFailed() {
        this.innerStatus = InnerDeliveryStatus.ERROR;
    }

    public void markAsSuccess(String externalId, String externalStatus) {
        this.externalId = externalId;
        this.externalStatus = externalStatus;
    }
}
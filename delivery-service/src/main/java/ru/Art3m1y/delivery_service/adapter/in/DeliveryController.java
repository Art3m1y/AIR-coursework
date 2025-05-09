package ru.Art3m1y.delivery_service.adapter.in;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.Art3m1y.delivery_service.adapter.in.dto.CreateDeliveryRqDto;
import ru.Art3m1y.delivery_service.adapter.in.dto.CreateDeliveryRsDto;

@RestController
@RequestMapping("api/v1/deliveries")
@Slf4j
@RequiredArgsConstructor
public class DeliveryController {
    @PostMapping
    public CreateDeliveryRsDto createDelivery(@RequestBody CreateDeliveryRqDto requestDto) {
        throw new RuntimeException();
    }

    @DeleteMapping("{deliveryId}/cancel")
    public void cancelDelivery(@PathVariable String deliveryId) {
        System.out.println("Доставка отменена");
    }
}

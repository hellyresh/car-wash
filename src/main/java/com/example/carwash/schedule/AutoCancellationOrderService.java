package com.example.carwash.schedule;

import com.example.carwash.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoCancellationOrderService {

    private final OrderService orderService;
    private final String EVERY_MINUTE = "0 0/1 * * * ?";

    @Scheduled(cron = "@midnight")
    public void dailyCancellation() {
        orderService.cancelNotCheckedInOrders();
    }

    @Scheduled(cron = EVERY_MINUTE)
    private void permanentCancellation() {
        orderService.cancelClosestNotCheckedInOrders();
    }
}

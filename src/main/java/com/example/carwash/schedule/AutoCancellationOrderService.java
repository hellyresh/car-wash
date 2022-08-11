package com.example.carwash.schedule;

import com.example.carwash.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoCancellationOrder {
    private final OrderService orderService;
    @Scheduled(cron = "@midnight")
    public void everyMidnight() {
        orderService.cancelNotCheckedInOrders();
    }

    @Scheduled(cron = "0 0/3 * * * ?")
    private void everyMinute() {
        orderService.cancelClosestNotCheckedInOrders();
    }
}

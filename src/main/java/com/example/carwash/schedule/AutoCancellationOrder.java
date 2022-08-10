package com.example.carwash.schedule;

import com.example.carwash.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Slf4j
@Component
public class AutoCancellationOrder {
    OrderService orderService;
    @Scheduled(cron = "@midnight")
    public void scheduledTaskByCorn() {
        orderService.cancelClosestNotCheckedInOrders();
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    private void scheduledTask() {
        orderService.cancelNotCheckedInOrders();
    }
}

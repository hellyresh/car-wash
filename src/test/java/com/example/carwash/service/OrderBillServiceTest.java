package com.example.carwash.service;

import com.example.carwash.model.Offer;
import com.example.carwash.model.Order;
import com.example.carwash.repository.OrderBillRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderBillServiceTest {

    @Test
    void createBill() {

    }

    @MockBean
    OrderBillRepo orderBillRepo;
    OrderBillService orderBillService = new OrderBillService(orderBillRepo);

    @Test
    @DisplayName("Test operations with BigDecimal")
    void countPrice_validPriceAndDiscount_validResult() {
        Offer offer = new Offer();
        offer.setPrice(BigDecimal.valueOf(1200).setScale(2, RoundingMode.UNNECESSARY));
        Order order = new Order();
        order.setOffer(offer);
        order.setDiscount(10);
        assertEquals(new BigDecimal("1080.00"), orderBillService.countPrice(order));
    }
}
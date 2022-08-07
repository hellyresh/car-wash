package com.example.carwash.service;

import com.example.carwash.model.Order;
import com.example.carwash.model.OrderBill;
import com.example.carwash.repository.OrderBillRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderBillService {

    private final OrderBillRepo orderBillRepo;

    @Transactional
    public void createBill(Order order) {
        OrderBill orderBill = new OrderBill(order, countPrice(order), LocalDateTime.now());
        orderBillRepo.save(orderBill);
    }

    protected BigDecimal countPrice(Order order) {
        int discount = order.getDiscount();
        BigDecimal price = order.getOffer().getPrice();
        BigDecimal discountValue = price.multiply((BigDecimal.valueOf(discount * 0.01)));
        return price.add(discountValue.negate()).setScale(2, RoundingMode.HALF_UP);
    }
}

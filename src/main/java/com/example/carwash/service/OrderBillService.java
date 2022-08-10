package com.example.carwash.service;

import com.example.carwash.dto.orderBill.OrderBillDto;
import com.example.carwash.model.Order;
import com.example.carwash.model.OrderBill;
import com.example.carwash.repository.OrderBillRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderBillService {

    private final OrderBillRepo orderBillRepo;
    private final UserService userService;

    @Transactional
    public OrderBill createBill(Order order) {
        Integer discount = order.getDiscount();
        OrderBill orderBill = new OrderBill(order,
                discount == null ? order.getPrice() : calculatePrice(order),
                LocalDateTime.now());
        orderBillRepo.save(orderBill);
        return orderBill;
    }

    protected BigDecimal calculatePrice(Order order) {
        Integer discount = order.getDiscount();
        BigDecimal price = order.getOffer().getPrice();
        BigDecimal discountValue = price.multiply((BigDecimal.valueOf(discount * 0.01)));
        return price.add(discountValue.negate()).setScale(2, RoundingMode.HALF_UP);
    }

    public List<OrderBillDto> getBills() {
        return orderBillRepo.findByUserId(userService.getCurrentUser().getId());
    }
}

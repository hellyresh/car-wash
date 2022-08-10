package com.example.carwash.repository;

import com.example.carwash.dto.order.OrderDto;
import com.example.carwash.dto.orderBill.OrderBillDto;
import com.example.carwash.model.OrderBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderBillRepo extends JpaRepository<OrderBill, Long> {

    List<OrderBillDto> findByUserId(Long id);

    @Query(value = """
            select sum(b.price) from bills b
            where b.date between :start and :end"""
            , nativeQuery = true)
    BigDecimal calculateRevenue(@Param("start") LocalDate startDate, @Param("end") LocalDate endDate);
}

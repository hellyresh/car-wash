package com.example.carwash.repository;

import com.example.carwash.dto.orderBill.OrderBillDto;
import com.example.carwash.model.OrderBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderBillRepo extends JpaRepository<OrderBill, Long> {

    List<OrderBillDto> findByUserId(Long id);
}

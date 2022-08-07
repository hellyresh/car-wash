package com.example.carwash.repository;

import com.example.carwash.model.OrderBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBillRepo extends JpaRepository<OrderBill, Long> {
}

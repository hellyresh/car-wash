package com.example.carwash.repository;

import com.example.carwash.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query(value = """
            select distinct o.* from orders o
            where
                o.status in ('SUBMITTED')
            and
                cast(o.date_time as date) < cast(:now as date)"""
            , nativeQuery = true)
    List<Order> findAllByStatusAndDateTime(@Param("now") LocalDateTime now);
    @Query(value = """
            select distinct o.* from orders o
            where
                o.status in ('SUBMITTED')
            and
                cast(o.date_time as date) = cast(:now as date)
            and
                cast(o.date_time as time) <= (cast(:now as time) + interval '5 minutes')

            """
            , nativeQuery = true)
    List<Order> findClosestSubmittedByDateTime(@Param("now") LocalDateTime now);

    void findByBoxId(Long boxId);

    List<Order> findAllByBoxId(Long boxId);
}

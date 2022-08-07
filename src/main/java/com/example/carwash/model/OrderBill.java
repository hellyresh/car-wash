package com.example.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "bills")
@NoArgsConstructor
public class OrderBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @NotNull
    @Column(name = "price", scale = 2)
    private BigDecimal price;


    @Column(name = "date")
    private LocalDateTime dateTime;


    public OrderBill(Order order, BigDecimal price, LocalDateTime dateTime) {
        this.order = order;
        this.price = price;
        this.dateTime = dateTime;
    }
}

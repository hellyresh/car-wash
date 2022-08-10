package com.example.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "order_id")
    private Long orderId;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Column(name = "offer_name")
    private String offerName;

    @NotNull
    @Column(name = "price", scale = 2)
    private BigDecimal price;

    @Column(name = "date")
    private LocalDateTime dateTime;


    public OrderBill(Order order, BigDecimal price, LocalDateTime dateTime) {
        this.orderId = order.getId();
        this.userId = order.getUser().getId();
        this.offerName = order.getOffer().getName();
        this.price = price;
        this.dateTime = dateTime;
    }
}

package com.example.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
    @Length(min = 3)
    @Column(name = "offer_name", length = 30)
    private String offerName;

    @NotNull
    @Positive
    @Column(name = "price", scale = 2)
    private BigDecimal price;

    @NotNull
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

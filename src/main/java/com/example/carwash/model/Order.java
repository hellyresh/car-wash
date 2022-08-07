package com.example.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    //todo rollback to datetime&duration, price, change constructor
    @Column(name = "date")
    private LocalDateTime dateTime;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "discount")
    private Integer discount;

    @NotNull
    @Column(name = "price", scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    @NotNull
    private Box box;

    public Order(User user, Offer offer, OrderStatus status,
                 LocalDateTime dateTime, Duration duration, BigDecimal price, Box box) {
        this.user = user;
        this.offer = offer;
        this.status = status;
        this.dateTime = dateTime;
        this.duration = duration;
        this.price = price;
        this.box = box;
    }


}

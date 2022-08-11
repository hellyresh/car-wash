package com.example.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 15)
    private OrderStatus status;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "duration")
    @Positive
    private Integer duration;

    @Column(name = "discount")
    @Positive
    private Integer discount;

    @NotNull
    @Positive
    @Column(name = "price", scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    @NotNull
    private Box box;

    public Order(User user, Offer offer, OrderStatus status,
                 LocalDateTime dateTime, Integer duration, BigDecimal price, Box box) {
        this.user = user;
        this.offer = offer;
        this.status = status;
        this.dateTime = dateTime;
        this.duration = duration;
        this.price = price;
        this.box = box;
    }


}

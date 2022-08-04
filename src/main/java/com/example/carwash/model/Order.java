package com.example.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "date_time")
    private Instant dateTime;

    @Column(name = "discount")
    private int discount;

    @ManyToOne
    @JoinColumn(name = "box_id",referencedColumnName = "id")
    @NotNull
    private Box box;

    @Column(name = "price", scale = 2)
    private BigDecimal price;


    public Order(User user, Offer offer, Instant dateTime, Box box, BigDecimal price) {
        this.user = user;
        this.offer = offer;
        this.status = OrderStatus.UNCONFIRMED;
        this.dateTime = dateTime;
        this.box = box;
        this.price = price;
    }


}

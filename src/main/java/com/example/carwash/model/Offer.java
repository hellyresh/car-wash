package com.example.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;

@Entity
@Getter
@Setter
@Table(name = "offers")
@NoArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "price", scale = 2)
    private BigDecimal price;


    public Offer(String name, Duration duration, BigDecimal price) {
        this.name = name;
        this.duration = duration;
        this.price = price;
    }
}

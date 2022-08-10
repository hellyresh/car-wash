package com.example.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "offers")
@NoArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "duration")
    private Integer duration;

    @Positive
    @NotNull
    @Column(name = "price", scale = 2)
    private BigDecimal price;


    public Offer(String name, Integer duration, BigDecimal price) {
        this.name = name;
        this.duration = duration;
        this.price = price;
    }
}

package com.example.carwash.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@Table(name = "operators")
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "min_discount")
    @Positive
    @Max(100)
    private Integer minDiscount;

    @Positive
    @Max(100)
    @Column(name = "max_discount")
    private Integer maxDiscount;

    @OneToOne
    @JoinColumn(name = "box_id", referencedColumnName = "id", unique = true)
    private Box box;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;
}

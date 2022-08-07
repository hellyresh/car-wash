package com.example.carwash.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private Integer minDiscount;
    @Column(name = "max_discount")
    private Integer maxDiscount;


    @OneToOne
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    private Box box;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}

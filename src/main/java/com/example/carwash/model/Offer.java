package com.example.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
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

    @Column(name = "name", length = 30)
    @Length(min = 4, max = 30)
    private String name;

    @NotNull
    @Column(name = "duration")
    private Integer duration;

    @Positive
    @NotNull
    @Column(name = "price", scale = 2)
    private BigDecimal price;

}

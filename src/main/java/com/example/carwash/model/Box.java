package com.example.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "boxes")
@NoArgsConstructor
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "open_time", columnDefinition = "time default '10:00'")
    private LocalTime openTime;

    @Column(name = "close_time", columnDefinition = "time default '22:00'")
    private LocalTime closeTime;

    @Column(name = "coefficient")
    @Positive
    @NotNull
    private Double timeCoefficient;


    public Box(LocalTime openTime, LocalTime closeTime, Double timeCoefficient) {
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.timeCoefficient = timeCoefficient;
    }
}

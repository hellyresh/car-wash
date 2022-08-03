package com.example.carwash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "boxes")
@NoArgsConstructor
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @Column(name = "coefficient")
    private double timeCoefficient;

    public Box(LocalTime openTime, LocalTime closeTime, double timeCoefficient) {
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.timeCoefficient = timeCoefficient;
    }
}

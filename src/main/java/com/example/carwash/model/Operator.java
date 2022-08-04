package com.example.carwash.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("operator")
public class Operator extends User {

    @OneToOne
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    private Box box;

    @Column(name = "min_discount")
    private int min_discount;
    @Column(name = "max_discount")
    private int max_discount;
}

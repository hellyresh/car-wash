package com.example.carwash.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "first_name", length = 20)
    @Length(min = 2, max = 20)
    private String firstName;

    @Length(max = 20)
    @Column(name = "last_name", length = 20)
    private String lastName;

    @NotBlank
    @Column(name = "username", unique = true, length = 20)
    @Length(min = 3, max = 20)
    private String username;

    @NotBlank
    @Column(name = "password", length = 80)
    private String password;

    @Column(name = "role", length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @Fetch(FetchMode.JOIN)
    private Set<Order> orders = new HashSet<>();

}

package com.example.bookingroom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nameAccount;

    @Column(nullable = false)
    private String nameLogin;

    @Column
    private String email;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int rewardPoint;
}

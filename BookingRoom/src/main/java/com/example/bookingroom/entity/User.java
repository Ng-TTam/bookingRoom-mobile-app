package com.example.bookingRoom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameAccount;

    @Column(unique = true)
    private String nameLogin;
    private String password;

    private String email;
    private String number;
    private LocalDate birth;
    private String address;
    private int rewardPoint;
}

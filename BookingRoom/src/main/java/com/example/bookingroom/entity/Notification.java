package com.example.bookingroom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    private String image;

    @Column(nullable = false)
    private LocalDate dateCreate;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isSeen;

    @Column(nullable = false)
    private int objectId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}

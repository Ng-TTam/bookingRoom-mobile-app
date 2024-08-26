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
    private String title;
    private String image;
    private LocalDate dateCreate;
    private String type;
    private String content;
    private Boolean isSeen;
    private int objectId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}

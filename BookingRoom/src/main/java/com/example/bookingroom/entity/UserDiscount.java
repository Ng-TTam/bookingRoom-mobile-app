package com.example.bookingroom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_discount")
public class UserDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int used;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Discount discount;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
}

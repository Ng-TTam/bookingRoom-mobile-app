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

    /*have 3 status:
        da lay ma giam va su dung (0)
        da lay ma va chua su dung (1)
        da lay ma va het han -> xoa
     */
    private int used;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Discount discount;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
}

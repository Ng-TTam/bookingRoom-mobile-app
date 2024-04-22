package com.example.bookingroom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private int rewardPoint;

    @Column(nullable = false)
    private int leastAmountUsed;

    @Column(nullable = false)
    private int largestAmountReduce;

    @Column(nullable = false)
    private int reducedPrice;

    @Column(nullable = false)
    private int quality;

    @Column(nullable = false)
    private int usedQuality;

    @Column
    private String image;

    @Column(nullable = false)
    private LocalDate outOfDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;
}

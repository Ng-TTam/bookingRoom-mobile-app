package com.example.bookingRoom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String term;
    private String type;
    private int rewardPoint;
    private int leastAmountUsed;
    private int largestAmountReduce;
    private int reducedPrice;
    private int quality;
    private int usedQuality;
    private String image;
    private LocalDate outOfDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;
}

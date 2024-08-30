package com.example.bookingroom.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discount")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String term;
    String type;
    int rewardPoint;
    int leastAmountUsed;
    int largestAmountReduce;
    int reducedPrice;
    int quality;
    int usedQuality;
    String image;
    LocalDate outOfDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id",nullable = false)
    Hotel hotel;
}

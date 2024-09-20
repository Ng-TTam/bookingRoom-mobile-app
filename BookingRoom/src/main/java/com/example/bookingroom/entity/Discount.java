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
    String code;
    float discountValue;
    String discountType;  // percentage or amount
    LocalDate startDate;
    LocalDate endDate;
    float minPurchaseAmount;
    float maxPurchaseAmount;
    int usageLimit;
    boolean isActive;
    String term;
    String image;
    int rewardPoint;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id",nullable = false)
    Hotel hotel;
}

package com.example.bookingroom.dto.reqResp;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountDTO {
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
    HotelDTO hotel;
}

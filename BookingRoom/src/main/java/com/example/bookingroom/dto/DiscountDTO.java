package com.example.bookingroom.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiscountDTO {
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
    private HotelDTO hotelDTO;
}

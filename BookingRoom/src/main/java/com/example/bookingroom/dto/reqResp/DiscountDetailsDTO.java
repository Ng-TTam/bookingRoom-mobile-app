package com.example.bookingroom.dto.reqResp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountDetailsDTO {
        String code;
        float discountValue;
        String discountType;
        LocalDate startDate;
        LocalDate endDate;
        float minPurchaseAmount;
        float maxPurchaseAmount;
        int usageLimit;
        boolean isActive;
        String term;
        String image;
        int rewardPoint;

        HotelDTO hotel;
}

package com.example.bookingroom.dto.reqResp;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountDTO {
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

}

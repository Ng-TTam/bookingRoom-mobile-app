package com.example.bookingroom.dto.reqResp;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookedRoomDTO {
    int id;
    LocalDateTime checkIn;
    LocalDateTime checkOut;
    int price;
    boolean isCheckIn;
    boolean isCanceled;

    LocalDateTime createdAt;
    LocalDateTime updateAt;

    RoomDetailsDTO room;
    DiscountDTO discount;
}

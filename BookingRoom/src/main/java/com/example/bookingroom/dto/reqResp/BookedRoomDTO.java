package com.example.bookingroom.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookedRoomDTO {
    int id;
    LocalDate checkIn;
    LocalDate checkOut;
    int price;
    boolean isCheckIn;
    boolean isCanceled;
    RoomDTO room;
    DiscountDTO discount;
}

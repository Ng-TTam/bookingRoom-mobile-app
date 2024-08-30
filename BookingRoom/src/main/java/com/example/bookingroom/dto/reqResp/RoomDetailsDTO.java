package com.example.bookingroom.dto.reqResp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDetailsDTO {
    int id;
    String name;
    String type;
    int price;
    String description;
    String image;
    boolean isActive;
    HotelDTO hotel;
}

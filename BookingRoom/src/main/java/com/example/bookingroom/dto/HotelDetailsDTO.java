package com.example.bookingroom.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelDetailsDTO {
    int id;
    String name;
    float starLevel;
    String address;
    String description;
    String image;
    List<RoomDTO> rooms;
}

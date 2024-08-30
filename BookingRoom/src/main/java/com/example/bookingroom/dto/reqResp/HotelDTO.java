package com.example.bookingroom.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelDTO {
    int id;
    String name;
    float starLevel;
    String address;
    String description;
    String image;
}

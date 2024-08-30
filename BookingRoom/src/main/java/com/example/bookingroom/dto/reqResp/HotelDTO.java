package com.example.bookingroom.dto.reqResp;

import lombok.*;
import lombok.experimental.FieldDefaults;

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

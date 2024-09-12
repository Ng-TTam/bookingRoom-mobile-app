package com.example.bookingroom.dto.reqResp;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("isActive")
    boolean isActive;
    HotelDTO hotel;
}

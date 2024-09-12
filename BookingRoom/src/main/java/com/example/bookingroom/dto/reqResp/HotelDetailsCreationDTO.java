package com.example.bookingroom.dto.reqResp;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelDetailsCreationDTO {
    String name;
    float starLevel;
    String address;
    String description;
    ArrayList<RoomDTO> rooms;
}

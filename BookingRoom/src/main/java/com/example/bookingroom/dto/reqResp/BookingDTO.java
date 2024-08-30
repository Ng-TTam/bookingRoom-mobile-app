package com.example.bookingroom.dto;

import com.example.bookingroom.dto.response.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDTO {
    int id;
    String note;
    int totalPrice;
    boolean isCanceled;
    List<BookedRoomDTO> bookedRooms;
    UserResponse user;
}

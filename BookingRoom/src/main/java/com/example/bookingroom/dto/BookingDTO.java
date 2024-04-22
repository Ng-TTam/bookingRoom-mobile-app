package com.example.bookingroom.dto;

import com.example.bookingroom.entity.Hotel;
import lombok.Data;

import java.util.List;

@Data
public class BookingDTO {
    private int id;
    private String note;
    private int totalPrice;
    private boolean isCancled;
    private List<BookedRoomDTO> bookedRoomDTOs;
    private HotelDTO hotelDTO;
}

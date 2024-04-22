package com.example.bookingroom.service;

import com.example.bookingroom.dto.BookedRoomDTO;
import com.example.bookingroom.dto.BookingDTO;
import com.example.bookingroom.dto.HotelDTO;
import com.example.bookingroom.dto.UserDTO;

import java.util.List;

public interface BookedRoomService {
    List<BookedRoomDTO> getListBookedRoomByBooking(BookingDTO bookingDTO);
    void updateBookedRoom(BookedRoomDTO bookedRoomDTO);
}

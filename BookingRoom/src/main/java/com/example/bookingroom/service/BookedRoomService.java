package com.example.bookingRoom.service;

import com.example.bookingRoom.dto.BookedRoomDTO;
import com.example.bookingRoom.dto.BookingDTO;

import java.util.List;

public interface BookedRoomService {
    List<BookedRoomDTO> getListBookedRoomByBooking(BookingDTO bookingDTO);
    void updateBookedRoom(BookedRoomDTO bookedRoomDTO);
}

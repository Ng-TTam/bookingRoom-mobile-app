package com.example.bookingRoom.service;

import com.example.bookingRoom.dto.request.UserRequest;
import com.example.bookingRoom.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    List<BookingDTO> getListBookingByUser(UserRequest userDTO);
    BookingDTO getBookingById(int id);
    void updateBooking(BookingDTO bookingDTO);
}

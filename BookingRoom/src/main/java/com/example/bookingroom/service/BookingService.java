package com.example.bookingroom.service;

import com.example.bookingroom.dto.BookingDTO;
import com.example.bookingroom.dto.UserDTO;

import java.util.List;

public interface BookingService {
    List<BookingDTO> getListBookingByUser(UserDTO userDTO);
    BookingDTO getBookingById(int id);
    void updateBooking(BookingDTO bookingDTO);
}

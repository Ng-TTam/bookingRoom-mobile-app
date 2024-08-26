package com.example.bookingroom.service;

import com.example.bookingroom.dto.request.UserRequest;
import com.example.bookingroom.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    List<BookingDTO> getListBookingByUser(UserRequest userDTO);
    BookingDTO getBookingById(int id);
    void updateBooking(BookingDTO bookingDTO);
}

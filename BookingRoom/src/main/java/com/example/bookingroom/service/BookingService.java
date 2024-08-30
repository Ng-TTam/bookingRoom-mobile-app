package com.example.bookingroom.service;

import com.example.bookingroom.dto.reqResp.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO create(BookingDTO bookingDTO);
    List<BookingDTO> getListBookingByUser();
    BookingDTO getBookingById(int id);
    BookingDTO updateBooking(int bookingId,BookingDTO bookingDTO);
}

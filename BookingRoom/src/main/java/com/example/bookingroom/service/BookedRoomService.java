package com.example.bookingroom.service;

import com.example.bookingroom.dto.reqResp.BookedRoomDTO;
import com.example.bookingroom.dto.reqResp.BookingDTO;

import java.util.List;

public interface BookedRoomService {
    BookedRoomDTO getBookedRoom(int bookingId, int bookedRoomId);
    void updateBookedRoom(int bookedRoomId, BookedRoomDTO bookedRoomDTO);
}

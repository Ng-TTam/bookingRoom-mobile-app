package com.example.bookingroom.service;

import com.example.bookingroom.dto.HotelDTO;
import com.example.bookingroom.dto.RoomDTO;

public interface HotelService {
    HotelDTO getHotelById(int id);
    HotelDTO getHotelByRoom(RoomDTO roomDTO);
}

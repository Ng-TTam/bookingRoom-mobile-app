package com.example.bookingRoom.service;

import com.example.bookingRoom.dto.HotelDTO;
import com.example.bookingRoom.dto.RoomDTO;
import org.springframework.stereotype.Service;

@Service
public interface HotelService {
    HotelDTO getHotelById(int id);
    HotelDTO getHotelByRoom(RoomDTO roomDTO);
}

package com.example.bookingroom.service;

import com.example.bookingroom.dto.HotelDTO;
import com.example.bookingroom.dto.HotelDetailsDTO;
import com.example.bookingroom.dto.RoomDTO;
import com.example.bookingroom.dto.RoomDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    RoomDetailsDTO getRoom(int id);
    List<RoomDetailsDTO> getRooms();
    List<RoomDTO> getRoomsByHotelId(int hotelId);
    RoomDetailsDTO create(RoomDetailsDTO roomDetailsDTO);
    RoomDetailsDTO update(RoomDetailsDTO roomDetailsDTO);
    void delete(int id);
}

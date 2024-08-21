package com.example.bookingRoom.service;

import com.example.bookingRoom.dto.RoomDTO;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {
    RoomDTO getRoomById(int id);
}

package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.HotelDTO;
import com.example.bookingroom.dto.RoomDTO;
import com.example.bookingroom.entity.Room;
import com.example.bookingroom.repository.RoomRepository;
import com.example.bookingroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public RoomDTO getRoomById(int id) {
        Room room = roomRepository.findById(id);
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setName(room.getName());
        roomDTO.setType(room.getType());
        roomDTO.setDescription(room.getDescription());
        roomDTO.setImage(room.getImage());
        roomDTO.setPrice(room.getPrice());
        return roomDTO;
    }

}

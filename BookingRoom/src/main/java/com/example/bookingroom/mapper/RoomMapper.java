package com.example.bookingroom.mapper;

import com.example.bookingroom.dto.RoomDTO;
import com.example.bookingroom.dto.RoomDetailsDTO;
import com.example.bookingroom.entity.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDetailsDTO toRoomDetailsDTO(Room room);

    RoomDTO toRoomDTO(Room room);

    Room toRoom(RoomDetailsDTO roomDetailsDTO);

    Room toRoom(RoomDTO roomDTO);
}

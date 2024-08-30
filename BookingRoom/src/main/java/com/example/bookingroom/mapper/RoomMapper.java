package com.example.bookingroom.mapper;

import com.example.bookingroom.dto.reqResp.RoomDTO;
import com.example.bookingroom.dto.reqResp.RoomDetailsDTO;
import com.example.bookingroom.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDetailsDTO toRoomDetailsDTO(Room room);

    RoomDTO toRoomDTO(Room room);

    Room toRoom(RoomDetailsDTO roomDetailsDTO);

    Room toRoom(RoomDTO roomDTO);

    @Mapping(target = "id", ignore = true)
    void updateRoom(@MappingTarget Room room, RoomDetailsDTO roomDetailsDTO);
}

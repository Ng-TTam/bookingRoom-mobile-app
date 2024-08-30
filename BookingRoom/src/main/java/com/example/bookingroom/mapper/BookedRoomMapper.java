package com.example.bookingroom.mapper;

import com.example.bookingroom.dto.reqResp.BookedRoomDTO;
import com.example.bookingroom.entity.BookedRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookedRoomMapper {
    BookedRoom toBookedRoom(BookedRoomDTO bookedRoomDTO);

    BookedRoomDTO toBookedRoomDTO(BookedRoom bookedRoom);

    @Mapping(target = "id", ignore = true)
    void updateBookedRoom(@MappingTarget BookedRoom bookedRoom, BookedRoomDTO bookedRoomDTO);
}

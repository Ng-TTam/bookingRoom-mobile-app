package com.example.bookingroom.service;

import com.example.bookingroom.dto.reqResp.RoomDTO;
import com.example.bookingroom.dto.reqResp.RoomDetailsDTO;
import com.example.bookingroom.dto.response.PageResponse;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {
    RoomDetailsDTO getRoom(int hotelId, int roomId);
    PageResponse<RoomDTO> getRoomsByHotelId(int hotelId, int page, int size);
    RoomDetailsDTO create(RoomDetailsDTO roomDetailsDTO);
    RoomDetailsDTO update(int roomId, RoomDetailsDTO roomDetailsDTO);
    void delete(int id);
}

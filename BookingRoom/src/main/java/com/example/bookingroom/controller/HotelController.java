package com.example.bookingroom.controller;

import com.example.bookingroom.dto.reqResp.RoomDetailsDTO;
import com.example.bookingroom.dto.response.ApiResponse;
import com.example.bookingroom.dto.reqResp.HotelDTO;
import com.example.bookingroom.dto.reqResp.HotelDetailsDTO;
import com.example.bookingroom.dto.reqResp.RoomDTO;
import com.example.bookingroom.service.HotelService;
import com.example.bookingroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    HotelService hotelService;
    @Autowired
    RoomService roomService;

    @PostMapping
    public ApiResponse<HotelDetailsDTO> createHotel(@RequestBody HotelDetailsDTO hotelDetailsDTO){
        return ApiResponse.<HotelDetailsDTO>builder()
                .result(hotelService.create(hotelDetailsDTO))
                .build();
    }

    @GetMapping
    public ApiResponse<List<HotelDTO>> getHotels(){
        return ApiResponse.<List<HotelDTO>>builder()
                .result(hotelService.getHotels())
                .build();
    }

    @GetMapping("/{hotelId}")
    public ApiResponse<HotelDetailsDTO> getHotel(@PathVariable int hotelId){
        return ApiResponse.<HotelDetailsDTO>builder()
                .result(hotelService.getHotel(hotelId))
                .build();
    }

    @GetMapping("/{hotelId}/rooms")
    public ApiResponse<List<RoomDTO>> getRooms(@PathVariable int hotelId){
        return ApiResponse.<List<RoomDTO>>builder()
                .result(roomService.getRoomsByHotelId(hotelId))
                .build();
    }

    @PutMapping("/{hotelId}")
    public ApiResponse<HotelDTO> editHotel(@PathVariable int hotelId, @RequestBody HotelDTO hotelDTO){
        return ApiResponse.<HotelDTO>builder()
                .result(hotelService.update(hotelId, hotelDTO))
                .build();
    }

    @GetMapping("/{hotelId}/rooms/{roomId}")
    public ApiResponse<RoomDetailsDTO> getRoom(@PathVariable int hotelId, @PathVariable int roomId){
        return ApiResponse.<RoomDetailsDTO>builder()
                .result(roomService.getRoom(hotelId, roomId))
                .build();
    }
}

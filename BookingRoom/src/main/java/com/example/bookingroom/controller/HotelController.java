package com.example.bookingroom.controller;

import com.example.bookingroom.dto.ApiResponse;
import com.example.bookingroom.dto.HotelDTO;
import com.example.bookingroom.dto.HotelDetailsDTO;
import com.example.bookingroom.dto.RoomDTO;
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

    @GetMapping("/{hotel_id}")
    public ApiResponse<HotelDetailsDTO> getHotel(@PathVariable int hotelId){
        return ApiResponse.<HotelDetailsDTO>builder()
                .result(hotelService.getHotel(hotelId))
                .build();
    }

    @GetMapping("/{hotel_id}/rooms")
    public ApiResponse<List<RoomDTO>> getRooms(@PathVariable int hotelId){
        return ApiResponse.<List<RoomDTO>>builder()
                .result(roomService.getRoomsByHotelId(hotelId))
                .build();
    }
}

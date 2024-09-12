package com.example.bookingroom.controller;

import com.example.bookingroom.dto.reqResp.*;
import com.example.bookingroom.dto.response.ApiResponse;
import com.example.bookingroom.dto.response.PageResponse;
import com.example.bookingroom.exception.AppException;
import com.example.bookingroom.exception.ErrorCode;
import com.example.bookingroom.service.HotelService;
import com.example.bookingroom.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@Slf4j
public class HotelController {
    @Autowired
    HotelService hotelService;
    @Autowired
    RoomService roomService;

    @PostMapping
    public ApiResponse<HotelDetailsDTO> createHotel(@RequestPart("hotel") String hotelDetailsJson,
                                                    @RequestPart("photo") MultipartFile imageFile){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            HotelDetailsCreationDTO hotelDetailsCreationDTO = objectMapper.readValue(hotelDetailsJson, HotelDetailsCreationDTO.class);

            return ApiResponse.<HotelDetailsDTO>builder()
                .result(hotelService.create(hotelDetailsCreationDTO, imageFile))
                .build();
        } catch (Exception e) {
//            throw new AppException(ErrorCode.BLANK_IMAGE);
            throw new RuntimeException(e);
        }
    }

    //get hotel without pagination
//    @GetMapping
//    public ApiResponse<List<HotelDTO>> getHotels(){
//        return ApiResponse.<List<HotelDTO>>builder()
//                .result(hotelService.getHotels())
//                .build();
//    }

    //get hotel have pagination
    @GetMapping
    public ApiResponse<PageResponse<HotelDTO>> getHotels(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        return ApiResponse.<PageResponse<HotelDTO>>builder()
                .result(hotelService.getHotels(page, size))
                .build();
    }

    @GetMapping("/{hotelId}")
    public ApiResponse<HotelDetailsDTO> getHotel(@PathVariable int hotelId){
        return ApiResponse.<HotelDetailsDTO>builder()
                .result(hotelService.getHotel(hotelId))
                .build();
    }

    //get room without pagination
//    @GetMapping("/{hotelId}/rooms")
//    public ApiResponse<List<RoomDTO>> getRooms(@PathVariable int hotelId){
//        return ApiResponse.<List<RoomDTO>>builder()
//                .result(roomService.getRoomsByHotelId(hotelId))
//                .build();
//    }

    //get rooms using pagination
    @GetMapping("/{hotelId}/rooms")
    public ApiResponse<PageResponse<RoomDTO>> getRooms(@PathVariable int hotelId,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                               @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        return ApiResponse.<PageResponse<RoomDTO>>builder()
                .result(roomService.getRoomsByHotelId(hotelId, page, size))
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

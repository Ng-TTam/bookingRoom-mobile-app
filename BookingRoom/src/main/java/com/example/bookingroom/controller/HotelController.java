package com.example.bookingroom.controller;

import com.example.bookingroom.dto.reqResp.*;
import com.example.bookingroom.dto.response.ApiResponse;
import com.example.bookingroom.dto.response.PageResponse;
import com.example.bookingroom.exception.AppException;
import com.example.bookingroom.exception.ErrorCode;
import com.example.bookingroom.service.DiscountService;
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
    @Autowired
    DiscountService discountService;

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

    @GetMapping("/{hotelId}/discounts")
    public ApiResponse<PageResponse<DiscountDTO>> getDiscounts(@PathVariable int hotelId,
                                                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                               @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        return ApiResponse.<PageResponse<DiscountDTO>>builder()
                .result(discountService.getDiscounts(hotelId, page, size))
                .build();
    }

    @GetMapping("/{hotelId}/discounts/{discountId}")
    public ApiResponse<DiscountDetailsDTO> getDiscounts(@PathVariable int hotelId, @PathVariable String discountId){
        return ApiResponse.<DiscountDetailsDTO>builder()
                .result(discountService.getDiscount(hotelId, discountId))
                .build();
    }

    @PostMapping("/{hotelId}/discounts")
    public ApiResponse<DiscountDetailsDTO> createDiscount(@PathVariable int hotelId, @RequestBody DiscountDetailsDTO discountDetailsDTO){
        return ApiResponse.<DiscountDetailsDTO>builder()
                .result(discountService.create(discountDetailsDTO, hotelId))
                .build();
    }

    @PutMapping("/{hotelId}/discounts/{discountId}")
    public ApiResponse<DiscountDTO> createDiscount(@PathVariable int hotelId, @RequestBody DiscountDTO discountDTO){
        return ApiResponse.<DiscountDTO>builder()
                .result(discountService.updateDiscount(discountDTO))
                .build();
    }

    @DeleteMapping("/{hotelId}/discounts/{discountId}")
    public ApiResponse<Void> deleteDiscount(@PathVariable int hotelId, @PathVariable String discountId){
        discountService.delete(hotelId, discountId);
        return ApiResponse.<Void>builder().build();
    }
}

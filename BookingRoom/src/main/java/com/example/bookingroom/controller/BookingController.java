package com.example.bookingroom.controller;

import com.example.bookingroom.dto.response.ApiResponse;
import com.example.bookingroom.dto.reqResp.BookingDTO;
import com.example.bookingroom.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @PostMapping
    public ApiResponse<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO){
        return ApiResponse.<BookingDTO>builder()
                .result(bookingService.create(bookingDTO))
                .build();
    }

    @GetMapping("/all")
    //get booking of user
    public ApiResponse<List<BookingDTO>> getAllBookingUser(){
        return ApiResponse.<List<BookingDTO>>builder()
                .result(bookingService.getListBookingByUser())
                .build();
    }

    @GetMapping("/detail/{id}")
    public BookingDTO getBooking(@PathVariable("id") int id){
                return bookingService.getBookingById(id);
    }

    @PostMapping("/update/{bookingId}")
    public ApiResponse<BookingDTO> updateBooking(@PathVariable int bookingId, @RequestBody BookingDTO bookingDTO){
        return ApiResponse.<BookingDTO>builder()
                .result(bookingService.updateBooking(bookingId, bookingDTO))
                .build();
    }
}

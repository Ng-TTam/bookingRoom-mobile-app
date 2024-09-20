package com.example.bookingroom.controller;

import com.example.bookingroom.dto.reqResp.DiscountDetailsDTO;
import com.example.bookingroom.dto.response.ApiResponse;
import com.example.bookingroom.dto.response.PageResponse;
import com.example.bookingroom.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @GetMapping("/all")
    public ApiResponse<PageResponse<DiscountDetailsDTO>> getListDiscounts(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size){
        return ApiResponse.<PageResponse<DiscountDetailsDTO>>builder()
                .result(discountService.getDiscounts(page, size))
                .build();
    }

}

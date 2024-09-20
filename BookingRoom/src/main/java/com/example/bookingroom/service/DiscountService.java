package com.example.bookingroom.service;

import com.example.bookingroom.dto.reqResp.DiscountDetailsDTO;
import com.example.bookingroom.dto.reqResp.HotelDTO;
import com.example.bookingroom.dto.request.UserRequest;
import com.example.bookingroom.dto.reqResp.DiscountDTO;
import com.example.bookingroom.dto.response.PageResponse;
import com.example.bookingroom.entity.Discount;
import com.example.bookingroom.entity.User;

import java.util.List;

public interface DiscountService {
    DiscountDetailsDTO create(DiscountDetailsDTO discountDetailsDTO, int hotelId);
    PageResponse<DiscountDetailsDTO> getDiscounts(int page, int size);
    PageResponse<DiscountDTO> getDiscounts(int hotelId, int page, int size);
    DiscountDetailsDTO getDiscount(int hotelId, String code);
    DiscountDTO updateDiscount(DiscountDTO discountDTO);
    void delete(int hotelId, String code);

    boolean checkDiscountUsed(User user, Discount discount);
}

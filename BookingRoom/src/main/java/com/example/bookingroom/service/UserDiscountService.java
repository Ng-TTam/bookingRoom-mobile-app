package com.example.bookingroom.service;

import com.example.bookingroom.dto.request.UserRequest;
import com.example.bookingroom.dto.reqResp.DiscountDTO;

public interface UserDiscountService {
    void addUserDiscount(UserRequest userDTO, DiscountDTO discountDTO);
}

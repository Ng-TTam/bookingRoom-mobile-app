package com.example.bookingroom.service;

import com.example.bookingroom.dto.request.UserRequest;
import com.example.bookingroom.dto.DiscountDTO;

public interface UserDiscountService {
    void addUserDiscount(UserRequest userDTO, DiscountDTO discountDTO);
}

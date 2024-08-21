package com.example.bookingRoom.service;

import com.example.bookingRoom.dto.request.UserRequest;
import com.example.bookingRoom.dto.DiscountDTO;

public interface UserDiscountService {
    void addUserDiscount(UserRequest userDTO, DiscountDTO discountDTO);
}

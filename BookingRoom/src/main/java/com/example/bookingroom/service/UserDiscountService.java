package com.example.bookingroom.service;

import com.example.bookingroom.dto.DiscountDTO;
import com.example.bookingroom.dto.UserDTO;

public interface UserDiscountService {
    void addUserDiscount(UserDTO userDTO, DiscountDTO discountDTO);
}

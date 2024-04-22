package com.example.bookingroom.service;

import com.example.bookingroom.dto.DiscountDTO;
import com.example.bookingroom.dto.UserDTO;

import java.util.List;

public interface DiscountService {
    List<DiscountDTO> getListDiscountUserUnowner(UserDTO userDTO);
    List<DiscountDTO> getListDiscountUserOwner(UserDTO userDTO);
    void exchangeDiscount(UserDTO userDTO, DiscountDTO discountDTO);
    DiscountDTO getDiscountByID(int id);
    void addDiscount(DiscountDTO discountDTO);
}

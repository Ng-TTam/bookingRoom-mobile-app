package com.example.bookingRoom.service;

import com.example.bookingRoom.dto.request.UserRequest;
import com.example.bookingRoom.dto.DiscountDTO;

import java.util.List;

public interface DiscountService {
    List<DiscountDTO> getListDiscountUserUnowner(UserRequest userDTO);
    List<DiscountDTO> getListDiscountUserOwner(UserRequest userDTO);
    void exchangeDiscount(UserRequest userDTO, DiscountDTO discountDTO);
    DiscountDTO getDiscountByID(int id);
    void addDiscount(DiscountDTO discountDTO);
}

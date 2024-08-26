package com.example.bookingroom.service;

import com.example.bookingroom.dto.request.UserRequest;
import com.example.bookingroom.dto.DiscountDTO;

import java.util.List;

public interface DiscountService {
    List<DiscountDTO> getListDiscountUserUnowner(UserRequest userDTO);
    List<DiscountDTO> getListDiscountUserOwner(UserRequest userDTO);
    void exchangeDiscount(UserRequest userDTO, DiscountDTO discountDTO);
    DiscountDTO getDiscountByID(int id);
    void addDiscount(DiscountDTO discountDTO);
}

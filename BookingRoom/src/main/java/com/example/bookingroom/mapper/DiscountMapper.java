package com.example.bookingroom.mapper;

import com.example.bookingroom.dto.reqResp.DiscountDTO;
import com.example.bookingroom.dto.reqResp.DiscountDetailsDTO;
import com.example.bookingroom.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    Discount toDiscount(DiscountDTO discountDTO);

    Discount toDiscount(DiscountDetailsDTO discountDetailsDTO);

    DiscountDTO toDiscountDTO(Discount discount);

    DiscountDetailsDTO toDiscountDetailsDTO(Discount discount);

    void updateDiscount(@MappingTarget Discount discount, DiscountDTO discountDTO);
}

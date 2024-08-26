package com.example.bookingroom.mapper;

import com.example.bookingroom.dto.HotelDTO;
import com.example.bookingroom.dto.HotelDetailsDTO;
import com.example.bookingroom.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelDTO toHotelDTO(Hotel hotel);

    HotelDetailsDTO toHotelDetailsDTO(Hotel hotel);

    Hotel toHotel(HotelDTO hotelDTO);

    Hotel toHotel(HotelDetailsDTO hotelDetailsDTO);
}

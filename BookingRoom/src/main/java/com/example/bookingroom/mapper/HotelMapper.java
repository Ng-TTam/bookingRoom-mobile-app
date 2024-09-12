package com.example.bookingroom.mapper;

import com.example.bookingroom.dto.reqResp.HotelDTO;
import com.example.bookingroom.dto.reqResp.HotelDetailsCreationDTO;
import com.example.bookingroom.dto.reqResp.HotelDetailsDTO;
import com.example.bookingroom.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelDTO toHotelDTO(Hotel hotel);

    HotelDetailsDTO toHotelDetailsDTO(Hotel hotel);

    @Mapping(target = "image", ignore = true)
    HotelDetailsDTO toHotelDetailsDTO(HotelDetailsCreationDTO hotelDetailsCreationDTO);

    Hotel toHotel(HotelDTO hotelDTO);

//    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "rooms", source = "rooms")
    Hotel toHotel(HotelDetailsDTO hotelDetailsDTO);

    @Mapping(target = "id", ignore = true)
    void updateHotel(@MappingTarget Hotel hotel, HotelDTO hotelDTO);
}

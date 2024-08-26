package com.example.bookingroom.service;

import com.example.bookingroom.dto.HotelDTO;
import com.example.bookingroom.dto.HotelDetailsDTO;
import com.example.bookingroom.dto.RoomDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    HotelDetailsDTO getHotel(int id);
    List<HotelDTO> getHotels();
    List<HotelDetailsDTO> getHotelsDetails();
    HotelDetailsDTO create(HotelDetailsDTO hotelDetailsDTO);
    HotelDetailsDTO update(HotelDetailsDTO hotelDetailsDTO);
//    void delete(HotelDetailsDTO hotelDetailsDTO);
}

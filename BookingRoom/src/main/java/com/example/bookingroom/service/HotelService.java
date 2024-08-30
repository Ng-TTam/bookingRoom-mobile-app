package com.example.bookingroom.service;

import com.example.bookingroom.dto.reqResp.HotelDTO;
import com.example.bookingroom.dto.reqResp.HotelDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    HotelDetailsDTO getHotel(int id);
    List<HotelDTO> getHotels();
    List<HotelDetailsDTO> getHotelsDetails();
    HotelDetailsDTO create(HotelDetailsDTO hotelDetailsDTO);
    HotelDTO update(int hotelId, HotelDTO hotelDetailsDTO);
//    void delete(HotelDetailsDTO hotelDetailsDTO);
}

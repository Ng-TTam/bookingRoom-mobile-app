package com.example.bookingroom.service;

import com.example.bookingroom.dto.reqResp.HotelDTO;
import com.example.bookingroom.dto.reqResp.HotelDetailsCreationDTO;
import com.example.bookingroom.dto.reqResp.HotelDetailsDTO;
import com.example.bookingroom.dto.response.PageResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface HotelService {
    HotelDetailsDTO getHotel(int id);
    List<HotelDTO> getHotels();
    List<HotelDetailsDTO> getHotelsDetails();
    HotelDetailsDTO create(HotelDetailsCreationDTO hotelDetailsCreationDTO, MultipartFile imageFile);
    HotelDTO update(int hotelId, HotelDTO hotelDetailsDTO);
//    void delete(HotelDetailsDTO hotelDetailsDTO);
    PageResponse<HotelDTO> getHotels(int page, int size);
}

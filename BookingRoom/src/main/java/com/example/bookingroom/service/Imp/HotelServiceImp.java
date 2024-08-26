package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.HotelDTO;
import com.example.bookingroom.dto.HotelDetailsDTO;
import com.example.bookingroom.dto.RoomDTO;
import com.example.bookingroom.mapper.HotelMapper;
import com.example.bookingroom.repository.HotelRepository;
import com.example.bookingroom.repository.RoomRepository;
import com.example.bookingroom.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImp implements HotelService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelMapper hotelMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public HotelDetailsDTO getHotel(int id) {
        return hotelMapper.toHotelDetailsDTO(hotelRepository.findById(id));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<HotelDTO> getHotels() {
        var hotels = hotelRepository.findAll();
        return hotels.stream().map(hotelMapper::toHotelDTO).toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<HotelDetailsDTO> getHotelsDetails() {
        var hotels = hotelRepository.findAll();
        return hotels.stream().map(hotelMapper::toHotelDetailsDTO).toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public HotelDetailsDTO create(HotelDetailsDTO hotelDetailsDTO) {
        return hotelMapper.toHotelDetailsDTO(
                hotelRepository.save(hotelMapper.toHotel(hotelDetailsDTO))
        );
    }

    @Override
    public HotelDetailsDTO update(HotelDetailsDTO hotelDetailsDTO) {
        return null;
    }

}

package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.reqResp.HotelDTO;
import com.example.bookingroom.dto.reqResp.HotelDetailsDTO;
import com.example.bookingroom.entity.Hotel;
import com.example.bookingroom.exception.AppException;
import com.example.bookingroom.exception.ErrorCode;
import com.example.bookingroom.mapper.HotelMapper;
import com.example.bookingroom.mapper.RoomMapper;
import com.example.bookingroom.repository.HotelRepository;
import com.example.bookingroom.repository.RoomRepository;
import com.example.bookingroom.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelServiceImp implements HotelService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    RoomMapper roomMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public HotelDetailsDTO getHotel(int id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_EXISTED));
        return hotelMapper.toHotelDetailsDTO(hotel);
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
    @Transactional
    public HotelDetailsDTO create(HotelDetailsDTO hotelDetailsDTO) {
        var hotel = hotelRepository.save(hotelMapper.toHotel(hotelDetailsDTO));
        hotelDetailsDTO.getRooms().forEach(roomDTO -> {
            var room = roomMapper.toRoom(roomDTO);
            room.setHotel(hotel);
            roomRepository.save(room);
        });
        return hotelMapper.toHotelDetailsDTO(hotel);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public HotelDTO update(int hotelId,HotelDTO hotelDTO) {
        var hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_EXISTED));
        hotelMapper.updateHotel(hotel, hotelDTO);
        return hotelMapper.toHotelDTO(hotelRepository.save(hotel));
    }

}

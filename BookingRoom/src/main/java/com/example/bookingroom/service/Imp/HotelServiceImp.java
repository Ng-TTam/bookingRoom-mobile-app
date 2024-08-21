package com.example.bookingRoom.service.Imp;

import com.example.bookingRoom.dto.HotelDTO;
import com.example.bookingRoom.dto.RoomDTO;
import com.example.bookingRoom.repository.HotelRepository;
import com.example.bookingRoom.repository.RoomRepository;
import com.example.bookingRoom.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImp implements HotelService {
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public HotelDTO getHotelById(int id) {
        HotelDTO hotelDTO = new HotelDTO();
//        Hotel hotel = hotelRepository.findById(id);
//        hotelDTO.setId(hotel.getId());
//        hotelDTO.setName(hotel.getName());
//        hotelDTO.setAddress(hotel.getAddress());
//        hotelDTO.setDescription(hotel.getDescription());
//        hotelDTO.setStarLevel(hotel.getStarLevel());
//        hotelDTO.setImage(hotel.getImage());
        //hotelDTO.setRoomDTOs(hotel.getRooms());
        return hotelDTO;
    }

    @Override
    public HotelDTO getHotelByRoom(RoomDTO roomDTO) {
        HotelDTO hotelDTO = new HotelDTO();
//        Hotel hotel = roomRepository.findById(roomDTO.getId()).getHotel();
//        hotelDTO.setId(hotel.getId());
//        hotelDTO.setName(hotel.getName());
//        hotelDTO.setAddress(hotel.getAddress());
//        hotelDTO.setDescription(hotel.getDescription());
//        hotelDTO.setStarLevel(hotel.getStarLevel());
//        hotelDTO.setImage(hotel.getImage());
        return hotelDTO;
    }
}

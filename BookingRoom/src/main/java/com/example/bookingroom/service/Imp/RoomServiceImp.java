package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.HotelDTO;
import com.example.bookingroom.dto.HotelDetailsDTO;
import com.example.bookingroom.dto.RoomDTO;
import com.example.bookingroom.dto.RoomDetailsDTO;
import com.example.bookingroom.entity.Hotel;
import com.example.bookingroom.mapper.HotelMapper;
import com.example.bookingroom.mapper.RoomMapper;
import com.example.bookingroom.repository.HotelRepository;
import com.example.bookingroom.repository.RoomRepository;
import com.example.bookingroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    HotelMapper hotelMapper;

    @Override
    public RoomDetailsDTO getRoom(int id) {
        return roomMapper.toRoomDetailsDTO(roomRepository.findById(id));
    }

    @Override
    public List<RoomDetailsDTO> getRooms() {
        var rooms = roomRepository.findAll();
        return rooms.stream().map(roomMapper::toRoomDetailsDTO).toList();
    }

    @Override
    public List<RoomDTO> getRoomsByHotelId(int hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId);
        var rooms = roomRepository.findByHotel(hotel);
        return rooms.stream().map(roomMapper::toRoomDTO).toList();
    }

    @Override
    public RoomDetailsDTO create(RoomDetailsDTO roomDetailsDTO) {
        return roomMapper.toRoomDetailsDTO(
                roomRepository.save(roomMapper.toRoom(roomDetailsDTO)));
    }

    @Override
    public RoomDetailsDTO update(RoomDetailsDTO roomDetailsDTO) {
        return null;
    }

    @Override
    public void delete(int id) {
        roomRepository.deleteById(id);
    }

}

package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.BookedRoomDTO;
import com.example.bookingroom.dto.BookingDTO;
import com.example.bookingroom.dto.HotelDTO;
import com.example.bookingroom.dto.UserDTO;
import com.example.bookingroom.entity.BookedRoom;
import com.example.bookingroom.entity.Booking;
import com.example.bookingroom.entity.Hotel;
import com.example.bookingroom.repository.BookedRoomRepository;
import com.example.bookingroom.repository.BookingRepository;
import com.example.bookingroom.service.BookedRoomService;
import com.example.bookingroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookedRoomServiceImp implements BookedRoomService {
    @Autowired
    BookedRoomRepository bookedRoomRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomService roomService;

    @Override
    public List<BookedRoomDTO> getListBookedRoomByBooking(BookingDTO bookingDTO) {
        List<BookedRoom> bookedRooms = bookedRoomRepository.findByBooking(bookingRepository.findById(bookingDTO.getId()));
        List<BookedRoomDTO> bookedRoomDTOs = new ArrayList<>();
        for(BookedRoom bookedRoom: bookedRooms){
            BookedRoomDTO bookedRoomDTO = new BookedRoomDTO();
            bookedRoomDTO.setId(bookedRoom.getId());
            bookedRoomDTO.setRoomDTO(roomService.getRoomById(bookedRoom.getRoom().getId()));
            bookedRoomDTO.setPrice(bookedRoom.getPrice());
            bookedRoomDTO.setIsCheckIn(bookedRoom.getIsCheckIn());
            bookedRoomDTO.setCheckIn(bookedRoom.getCheckIn());
            bookedRoomDTO.setCheckOut(bookedRoom.getCheckOut());
            bookedRoomDTOs.add(bookedRoomDTO);
        }
        return bookedRoomDTOs;
    }

    @Override
    public void updateBookedRoom(BookedRoomDTO bookedRoomDTO) {
        BookedRoom bookedRoom = bookedRoomRepository.findById(bookedRoomDTO.getId());
        bookedRoom.setCheckIn(bookedRoomDTO.getCheckIn());
        bookedRoom.setCheckOut(bookedRoomDTO.getCheckOut());
        bookedRoom.setIsCheckIn(bookedRoomDTO.getIsCheckIn());
        bookedRoomRepository.save(bookedRoom);
    }
}

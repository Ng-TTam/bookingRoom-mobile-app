package com.example.bookingRoom.service.Imp;


import com.example.bookingRoom.dto.BookedRoomDTO;
import com.example.bookingRoom.dto.BookingDTO;
import com.example.bookingRoom.dto.request.UserRequest;
import com.example.bookingRoom.entity.BookedRoom;
import com.example.bookingRoom.entity.Booking;
import com.example.bookingRoom.repository.BookedRoomRepository;
import com.example.bookingRoom.repository.BookingRepository;
import com.example.bookingRoom.service.BookedRoomService;
import com.example.bookingRoom.service.BookingService;
import com.example.bookingRoom.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImp implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    BookedRoomRepository bookedRoomRepository;
    @Autowired
    BookedRoomService bookedRoomService;
    @Autowired
    HotelService hotelService;

    @Override
    public List<BookingDTO> getListBookingByUser(UserRequest userDTO) {
//        List<Booking> bookings = bookingRepository.findAll();
//        List<BookingDTO> bookingDTOS = new ArrayList<>();
//        for(Booking booking: bookings){
//            if(booking.getUser().getId() == userDTO.getId()) {
//                BookingDTO bookingDTO = new BookingDTO();
//                bookingDTO.setId(booking.getId());
//                bookingDTO.setNote(booking.getNote());
//                bookingDTO.setTotalPrice(booking.getTotalPrice());
//                bookingDTO.setCancled(booking.isCancled());
//
//                //get list bookedRoomDTOs => get hotel contains room
//                //bookedRoom contain rooms in the same hotel
//                List<BookedRoomDTO> bookedRoomDTOs = bookedRoomService.getListBookedRoomByBooking(bookingDTO);
//                bookingDTO.setBookedRoomDTOs(bookedRoomDTOs);
//                BookedRoomDTO bookedRoomDTO = bookedRoomDTOs.get(0);
//                bookingDTO.setHotelDTO(hotelService.getHotelByRoom(bookedRoomDTO.getRoomDTO()));
//                bookingDTOS.add(bookingDTO);
//            }
//        }
//        if(!bookingDTOS.isEmpty()) return bookingDTOS;
        return null;
    }

    @Override
    public BookingDTO getBookingById(int id) {
        Booking booking = bookingRepository.findById(id);
        BookingDTO bookingDTO = new BookingDTO();
//        bookingDTO.setId(booking.getId());
//        bookingDTO.setNote(booking.getNote());
//        bookingDTO.setTotalPrice(booking.getTotalPrice());
//        bookingDTO.setCancled(booking.isCancled());
//
//        //get list bookedRoomDTOs => get hotel contains room
//        //bookedRoom contain rooms in the same hotel
//        List<BookedRoomDTO> bookedRoomDTOs = bookedRoomService.getListBookedRoomByBooking(bookingDTO);
//        bookingDTO.setBookedRoomDTOs(bookedRoomDTOs);
//        BookedRoomDTO bookedRoomDTO = bookedRoomDTOs.get(0);
//        bookingDTO.setHotelDTO(hotelService.getHotelByRoom(bookedRoomDTO.getRoomDTO()));
        return bookingDTO;
    }

    @Override
    public void updateBooking(BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(bookingDTO.getId());
//        booking.setCancled(bookingDTO.isCancled());
//        booking.setTotalPrice(bookingDTO.getTotalPrice());
//
//        //update list bookedrooms table in booking
//        for(BookedRoomDTO bookedRoomDTO: bookingDTO.getBookedRoomDTOs()){
//            BookedRoom bookedRoom = bookedRoomRepository.findById(bookedRoomDTO.getId());
//            bookedRoom.setCheckIn(bookedRoomDTO.getCheckIn());
//            bookedRoom.setCheckOut(bookedRoomDTO.getCheckOut());
//            bookedRoom.setPrice(bookedRoomDTO.getPrice());
//            bookedRoomRepository.save(bookedRoom);
//        }

        bookingRepository.save(booking);
    }
}

package com.example.bookingroom.service.Imp;


import com.example.bookingroom.dto.reqResp.BookingDTO;
import com.example.bookingroom.entity.BookedRoom;
import com.example.bookingroom.entity.Booking;
import com.example.bookingroom.exception.AppException;
import com.example.bookingroom.exception.ErrorCode;
import com.example.bookingroom.mapper.BookedRoomMapper;
import com.example.bookingroom.mapper.BookingMapper;
import com.example.bookingroom.repository.BookedRoomRepository;
import com.example.bookingroom.repository.BookingRepository;
import com.example.bookingroom.repository.UserRepository;
import com.example.bookingroom.service.BookingService;
import com.example.bookingroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingServiceImp implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    BookingMapper bookingMapper;
    @Autowired
    BookedRoomMapper bookedRoomMapper;
    @Autowired
    BookedRoomRepository bookedRoomRepository;
    @Autowired
    UserServiceImp userService;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public BookingDTO create(BookingDTO bookingDTO) {
        var booking = bookingMapper.toBooking(bookingDTO);
        booking.setUser(userService.getUserInContext());
        Booking finalBooking = bookingRepository.save(booking);

        booking.getBookedRooms().forEach(bookedRoom -> {
            bookedRoom.setBooking(finalBooking);

            bookedRoomRepository.save(bookedRoom);
        });
        return bookingMapper.toBookingDTO(finalBooking);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<BookingDTO> getListBookingByUser() {
        var bookings = bookingRepository.findByUser(userService.getUserInContext());

        return bookings.stream().map(bookingMapper::toBookingDTO).toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public BookingDTO getBookingById(int id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.BOOKING_NOT_EXISTED)
        );
        return bookingMapper.toBookingDTO(booking);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public BookingDTO updateBooking(int bookingId, BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new AppException(ErrorCode.BOOKING_NOT_EXISTED)
        );

        bookingMapper.update(booking, bookingDTO);

        return bookingMapper.toBookingDTO(bookingRepository.save(booking));
    }
}

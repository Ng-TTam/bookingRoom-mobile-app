package com.example.bookingroom.mapper;

import com.example.bookingroom.dto.reqResp.BookingDTO;
import com.example.bookingroom.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface BookingMapper {
    Booking toBooking(BookingDTO bookingDTO);

    BookingDTO toBookingDTO(Booking booking);

    void update(@MappingTarget Booking booking, BookingDTO bookingDTO);
}

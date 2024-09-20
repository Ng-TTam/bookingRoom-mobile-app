package com.example.bookingroom.mapper;

import com.example.bookingroom.dto.reqResp.BookingDTO;
import com.example.bookingroom.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface BookingMapper {

    @Mapping(source = "canceled", target = "isCanceled", defaultValue = "false")
    Booking toBooking(BookingDTO bookingDTO);

    @Mapping(source = "canceled", target = "isCanceled", defaultValue = "false")
    BookingDTO toBookingDTO(Booking booking);

    void update(@MappingTarget Booking booking, BookingDTO bookingDTO);
}

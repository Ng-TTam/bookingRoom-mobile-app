package com.example.bookingroom.repository;

import com.example.bookingroom.entity.BookedRoom;
import com.example.bookingroom.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedRoomRepository extends JpaRepository<BookedRoom, Integer> {
    List<BookedRoom> findByBooking(Booking booking);
    BookedRoom findById(int id);
}

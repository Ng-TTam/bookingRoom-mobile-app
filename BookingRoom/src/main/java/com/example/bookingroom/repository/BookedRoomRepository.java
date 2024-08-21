package com.example.bookingRoom.repository;

import com.example.bookingRoom.entity.BookedRoom;
import com.example.bookingRoom.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedRoomRepository extends JpaRepository<BookedRoom, Integer> {
    List<BookedRoom> findByBooking(Booking booking);
    BookedRoom findById(int id);
}

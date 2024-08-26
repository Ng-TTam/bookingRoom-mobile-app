package com.example.bookingroom.repository;

import com.example.bookingroom.entity.Hotel;
import com.example.bookingroom.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findById(int id);
    List<Room> findByHotel(Hotel hotel);
}

package com.example.bookingRoom.repository;

import com.example.bookingRoom.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    Hotel findById(int id);
}

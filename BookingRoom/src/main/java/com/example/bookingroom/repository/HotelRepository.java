package com.example.bookingroom.repository;

import com.example.bookingroom.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    Hotel findById(int id);
}

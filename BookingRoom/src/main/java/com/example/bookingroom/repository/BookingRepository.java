package com.example.bookingroom.repository;

import com.example.bookingroom.entity.Booking;
import com.example.bookingroom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Optional<Booking> findById(int id);
    List<Booking> findByUser(User user);
}

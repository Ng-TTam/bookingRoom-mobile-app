package com.example.bookingroom.repository;

import com.example.bookingroom.entity.Discount;
import com.example.bookingroom.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, String> {
        Page<Discount> findByHotel(Hotel hotel, Pageable pageable);
}

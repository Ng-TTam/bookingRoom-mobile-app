package com.example.bookingroom.repository;

import com.example.bookingroom.entity.Discount;
import com.example.bookingroom.entity.User;
import com.example.bookingroom.entity.UserDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDiscountRepository extends JpaRepository<UserDiscount, Integer> {
    boolean existsByUserAndDiscount(User user, Discount discount);
}

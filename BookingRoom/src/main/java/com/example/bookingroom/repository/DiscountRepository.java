package com.example.bookingroom.repository;

import com.example.bookingroom.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
        Discount findById(int id);

        @Query("SELECT DISTINCT d FROM Discount d " +
                "INNER JOIN Hotel h ON d.hotel.id = h.id " +
                "LEFT JOIN UserDiscount ud ON d.id = ud.discount.id AND ud.user.id = :userId " +
                "WHERE ud.id IS NULL")
        List<Discount> findUnownedDiscountsByUserId(Integer userId);

        @Query("SELECT d FROM Discount d " +
                "INNER JOIN Hotel h ON d.hotel.id = h.id " +
                "INNER JOIN UserDiscount ud ON ud.discount.id = d.id " +
                "WHERE ud.user.id = :userId")
        List<Discount> findOwnedDiscountsByUserId(Integer userId);
}

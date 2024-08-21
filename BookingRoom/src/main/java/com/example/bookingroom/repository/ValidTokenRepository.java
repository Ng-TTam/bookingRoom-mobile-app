package com.example.bookingRoom.repository;

import com.example.bookingRoom.entity.ValidToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidTokenRepository extends JpaRepository<ValidToken, Integer> {
    ValidToken findByRefreshToken(String refreshToken);
    ValidToken findByToken(String token);
}

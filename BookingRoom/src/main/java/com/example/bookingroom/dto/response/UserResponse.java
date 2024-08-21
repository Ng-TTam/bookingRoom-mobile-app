package com.example.bookingRoom.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private int id;
    private String nameAccount;
    private String nameLogin;
    private String email;
    private String number;
    private LocalDate birth;
    private String address;
    private int rewardPoint;
}

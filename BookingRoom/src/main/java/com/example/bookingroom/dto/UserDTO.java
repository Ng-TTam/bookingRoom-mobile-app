package com.example.bookingroom.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserDTO {
    private int id;
    private String nameAccount;
    private String nameLogin;
    private String email;
    private String number;
    private LocalDate birth;
    private int rewardPoint;
}

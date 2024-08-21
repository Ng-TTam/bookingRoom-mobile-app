package com.example.bookingRoom.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserCreationRequest {
    private String nameAccount;
    private String nameLogin;
    private String password;

    private String email;
    private String number;
    private String address;
    private LocalDate birth;
}

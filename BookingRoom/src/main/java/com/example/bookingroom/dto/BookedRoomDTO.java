package com.example.bookingroom.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookedRoomDTO {
    private int id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int price;
    private int isCheckIn;
    private RoomDTO roomDTO;
}

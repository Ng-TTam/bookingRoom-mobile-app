package com.example.bookingroom.dto;

import lombok.Data;

import java.util.List;

@Data
public class HotelDTO {
    private int id;
    private String name;
    private int starLevel;
    private String address;
    private String description;
    private String image;
    private List<RoomDTO> roomDTOs;
}

package com.example.bookingroom.dto;

import lombok.Data;

@Data
public class RoomDTO {
    private int id;
    private String name;
    private String type;
    private int price;
    private String description;
    private String image;
}

package com.example.bookingroom.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NotificationDTO {
    private int id;
    private String title;
    private String image;
    private LocalDate dateCreate;
    private String type;
    private String content;
    private Boolean isSeen;
    private int objectId;
}

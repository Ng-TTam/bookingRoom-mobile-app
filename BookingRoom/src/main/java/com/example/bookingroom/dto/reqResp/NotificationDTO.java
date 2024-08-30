package com.example.bookingroom.dto.reqResp;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationDTO {
    int id;
    String title;
    String image;
    LocalDate dateCreate;
    String type;
    String content;
    Boolean isSeen;
    int objectId;
}

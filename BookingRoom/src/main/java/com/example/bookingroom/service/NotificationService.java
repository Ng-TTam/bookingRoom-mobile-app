package com.example.bookingRoom.service;

import com.example.bookingRoom.dto.request.UserRequest;
import com.example.bookingRoom.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> getListNotificationByUser(UserRequest userDTO);
    void addNotificationDiscount(NotificationDTO notificationDTO);
    void addNotificationBooking(UserRequest userDTO, NotificationDTO notificationDTO);
    void seenNotification(NotificationDTO notificationDTO);
    void seenAllNotification(UserRequest userDTO);
}

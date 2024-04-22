package com.example.bookingroom.service;

import com.example.bookingroom.dto.NotificationDTO;
import com.example.bookingroom.dto.UserDTO;
import com.example.bookingroom.entity.Notification;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> getListNotificationByUser(UserDTO userDTO);
    void addNotificationDiscount(NotificationDTO notificationDTO);
    void addNotificationBooking(UserDTO userDTO, NotificationDTO notificationDTO);
    void seenNotification(NotificationDTO notificationDTO);
    void seenAllNotification(UserDTO userDTO);
}

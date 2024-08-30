package com.example.bookingroom.service;

import com.example.bookingroom.dto.request.UserRequest;
import com.example.bookingroom.dto.reqResp.NotificationDTO;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> getListNotificationByUser(UserRequest userDTO);
    void addNotificationDiscount(NotificationDTO notificationDTO);
    void addNotificationBooking(UserRequest userDTO, NotificationDTO notificationDTO);
    void seenNotification(NotificationDTO notificationDTO);
    void seenAllNotification(UserRequest userDTO);
}

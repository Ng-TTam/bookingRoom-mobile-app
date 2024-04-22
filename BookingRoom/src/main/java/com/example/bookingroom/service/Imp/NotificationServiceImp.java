package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.NotificationDTO;
import com.example.bookingroom.dto.UserDTO;
import com.example.bookingroom.entity.Notification;
import com.example.bookingroom.entity.User;
import com.example.bookingroom.repository.NotificationRepository;
import com.example.bookingroom.repository.UserRepository;
import com.example.bookingroom.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<NotificationDTO> getListNotificationByUser(UserDTO userDTO) {
        List<NotificationDTO> notificationDTOs = new ArrayList<>();
        List<Notification> notifications = notificationRepository.findByUserId(userDTO.getId());
        if(!notifications.isEmpty()){
            for(Notification notification: notifications){
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setId(notification.getId());
                notificationDTO.setContent(notification.getContent());
                notificationDTO.setImage(notification.getImage());
                notificationDTO.setDateCreate(notification.getDateCreate());
                notificationDTO.setTitle(notification.getTitle());
                notificationDTO.setIsSeen(notification.getIsSeen());
                notificationDTO.setObjectId(notification.getObjectId());
                notificationDTO.setType(notification.getType());

                notificationDTOs.add(notificationDTO);
            }
        }
        return notificationDTOs;
    }

    //discount notification: 1 discount send to n user
    @Override
    public void addNotificationDiscount(NotificationDTO notificationDTO) {
        List<User> users = userRepository.findAll();
        Notification notification = new Notification();
        notification.setContent(notificationDTO.getContent());
        notification.setImage(notificationDTO.getImage());
        notification.setDateCreate(LocalDate.now());
        notification.setTitle(notificationDTO.getTitle());
        notification.setIsSeen(false);
        notification.setObjectId(notificationDTO.getObjectId());
        notification.setType(notificationDTO.getType());
        for (User user: users){
            notification.setId(-1);
            notification.setUser(user);
            notificationRepository.save(notification);
        }
    }

    //booking notification: 1user change 1booking
    @Override
    public void addNotificationBooking(UserDTO userDTO, NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setContent(notificationDTO.getContent());
        notification.setImage(notificationDTO.getImage());
        notification.setDateCreate(LocalDate.now());
        notification.setTitle(notificationDTO.getTitle());
        notification.setIsSeen(false);
        notification.setObjectId(notificationDTO.getObjectId());
        notification.setType(notificationDTO.getType());
        notification.setUser(userRepository.findByNameLogin(userDTO.getNameLogin()));
        notificationRepository.save(notification);
    }

    @Override
    public void seenNotification(NotificationDTO notificationDTO) {
        Notification notification = notificationRepository.findById(notificationDTO.getId());
        notification.setIsSeen(true);
        notificationRepository.save(notification);
    }

    @Override
    public void seenAllNotification(UserDTO userDTO) {
        List<Notification> notifications = notificationRepository.findByUserId(userDTO.getId());
        for(Notification notification: notifications){
            notification.setIsSeen(true);
            notificationRepository.save(notification);
        }
    }

}

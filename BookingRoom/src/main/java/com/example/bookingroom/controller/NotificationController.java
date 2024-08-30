package com.example.bookingroom.controller;

import com.example.bookingroom.dto.reqResp.NotificationDTO;
import com.example.bookingroom.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/user/notification")
    public List<NotificationDTO> getListNotification(){
        throw new RuntimeException("Invalid token");
    }

    @PostMapping("/discount/notification")
    public ResponseEntity<String> sendDiscountNotification(@RequestBody NotificationDTO notificationDTO){
        notificationService.addNotificationDiscount(notificationDTO);
        return ResponseEntity.ok("Send discount notification successful");
    }

    @PostMapping("/booking/notification")
    public ResponseEntity<String> sendBookingNotification(){
        throw new RuntimeException("Invalid token");
    }

    @PostMapping("/notification/seen/{id}")
    public ResponseEntity<String> seenNotification(@PathVariable int id){
//                NotificationDTO notificationDTO = new NotificationDTO();
//                notificationDTO.setId(id);
//                notificationService.seenNotification(notificationDTO);
                return ResponseEntity.ok("seen");
    }

    @PostMapping("/notification/seen/all")
    public ResponseEntity<String> seenAllNotification(){
        throw new RuntimeException("Invalid token");
    }
}

package com.example.bookingroom.controller;

import com.example.bookingroom.dto.NotificationDTO;
import com.example.bookingroom.jwt.JwtTokenProvider;
import com.example.bookingroom.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    NotificationService notificationService;

    @GetMapping("/user/notification")
    public List<NotificationDTO> getListNotification(@RequestHeader("Authorization") String token){
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);

            if (tokenProvider.validateToken(jwtToken)) {
                List<NotificationDTO> notifications =  notificationService.getListNotificationByUser(tokenProvider.getUserFromToken(jwtToken));
                return notifications;
            }
        }
        throw new RuntimeException("Invalid token");
    }

    @PostMapping("/discount/notification")
    public ResponseEntity<String> sendDiscountNotification(@RequestBody NotificationDTO notificationDTO){
        notificationService.addNotificationDiscount(notificationDTO);
        return ResponseEntity.ok("Send discount notification successful");
    }

    @PostMapping("/booking/notification")
    public ResponseEntity<String> sendBookingNotification(@RequestHeader("Authorization") String token, @RequestBody NotificationDTO notificationDTO){
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);

            if (tokenProvider.validateToken(jwtToken)) {
                notificationService.addNotificationBooking(tokenProvider.getUserFromToken(jwtToken), notificationDTO);
                return ResponseEntity.ok("Send booking notification successful");
            }
        }
        throw new RuntimeException("Invalid token");
    }

    @PostMapping("/notification/seen/{id}")
    public ResponseEntity<String> seenNotification(@PathVariable int id){
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setId(id);
                notificationService.seenNotification(notificationDTO);
                return ResponseEntity.ok("seen");
    }

    @PostMapping("/notification/seen/all")
    public ResponseEntity<String> seenAllNotification(@RequestHeader("Authorization") String token){
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);

            if (tokenProvider.validateToken(jwtToken)) {
                notificationService.seenAllNotification(tokenProvider.getUserFromToken(jwtToken));
                return ResponseEntity.ok("success");
            }
        }
        throw new RuntimeException("Invalid token");
    }
}

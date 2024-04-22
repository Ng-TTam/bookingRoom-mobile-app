package com.example.bookingroom.controller;

import com.example.bookingroom.dto.BookedRoomDTO;
import com.example.bookingroom.dto.BookingDTO;
import com.example.bookingroom.dto.NotificationDTO;
import com.example.bookingroom.jwt.JwtTokenProvider;
import com.example.bookingroom.service.BookingService;
import com.example.bookingroom.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookingController {
    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    BookingService bookingService;

    @Autowired
    NotificationService notificationService;

    @GetMapping("/booking/all")
    public List<BookingDTO> getAllBookingUser(@RequestHeader("Authorization") String token){
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            if (tokenProvider.validateToken(jwtToken)) {
                return bookingService.getListBookingByUser(tokenProvider.getUserFromToken(jwtToken));
            }
        }
        throw new RuntimeException("Invalid token");
    }

    @GetMapping("/booking/detail/{id}")
    public BookingDTO getBooking(@RequestHeader("Authorization") String token,@PathVariable("id") int id){
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            if (tokenProvider.validateToken(jwtToken)) {
                return bookingService.getBookingById(id);
            }
        }
        throw new RuntimeException("Invalid token");
    }

    @PostMapping("/booking/update")
    public ResponseEntity<String> updateBooking(@RequestHeader("Authorization") String token,
                                                @RequestBody BookingDTO bookingDTO){
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            if (tokenProvider.validateToken(jwtToken)) {
                int totalPrice = 0;
                for(BookedRoomDTO bookedRoomDTO: bookingDTO.getBookedRoomDTOs()){
                    totalPrice += bookedRoomDTO.getPrice();
                }
                bookingDTO.setTotalPrice(totalPrice);
                bookingService.updateBooking(bookingDTO);

                List<BookedRoomDTO> bookedRoomDTOs = bookingDTO.getBookedRoomDTOs();

                String content = "";
                for(BookedRoomDTO bookedRoomDTO: bookingDTO.getBookedRoomDTOs()){
                    content += "Phòng " + bookedRoomDTO.getRoomDTO().getName() + ": " + String.valueOf(bookedRoomDTO.getCheckIn())
                            + " - " + String.valueOf(bookedRoomDTO.getCheckOut()) + "\n";
                }

                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setImage(bookingDTO.getHotelDTO().getImage());
                notificationDTO.setType("Lịch trình");
                notificationDTO.setIsSeen(false);
                notificationDTO.setObjectId(bookingDTO.getId());
                notificationDTO.setDateCreate(LocalDate.now());
                notificationDTO.setContent(content);
                notificationDTO.setTitle("Thay đổi lịch trình thành công");
                notificationService.addNotificationBooking(tokenProvider.getUserFromToken(jwtToken),notificationDTO);
                return ResponseEntity.ok("update success");
            }
        }
        throw new RuntimeException("Invalid token");
    }
}

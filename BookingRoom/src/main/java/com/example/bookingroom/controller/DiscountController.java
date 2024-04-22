package com.example.bookingroom.controller;

import com.example.bookingroom.dto.DiscountDTO;
import com.example.bookingroom.dto.NotificationDTO;
import com.example.bookingroom.dto.UserDTO;
import com.example.bookingroom.entity.Notification;
import com.example.bookingroom.jwt.JwtTokenProvider;
import com.example.bookingroom.service.DiscountService;
import com.example.bookingroom.service.NotificationService;
import com.example.bookingroom.service.UserDiscountService;
import com.example.bookingroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DiscountController {
    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    DiscountService discountService;

    @Autowired
    UserDiscountService userDiscountService;

    @Autowired
    UserService userService;

    @GetMapping("/user/discount-unowned")
    public List<DiscountDTO> getListDiscountUnowner(@RequestHeader("Authorization") String token){

        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);

            if (tokenProvider.validateToken(jwtToken)) {
                List<DiscountDTO> ListDiscountDTO =
                        discountService.getListDiscountUserUnowner(tokenProvider.getUserFromToken(jwtToken));
                return ListDiscountDTO;
            }
        }
        throw new RuntimeException("Invalid token");
    }

    @GetMapping("/user/discount-owned")
    public List<DiscountDTO> getListDiscountOwner(@RequestHeader("Authorization") String token){

        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);

            if (tokenProvider.validateToken(jwtToken)) {
                List<DiscountDTO> ListDiscountDTO =
                        discountService.getListDiscountUserOwner(tokenProvider.getUserFromToken(jwtToken));
                return ListDiscountDTO;
            }
        }
        throw new RuntimeException("Invalid token");
    }

    @PostMapping("/user/exchange-discount")
    public Boolean exchangeDiscount(@RequestHeader("Authorization") String token,
                                   @RequestBody DiscountDTO discountDTO){
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);

            if (tokenProvider.validateToken(jwtToken)) {
                UserDTO userDTO = tokenProvider.getUserFromToken(jwtToken);
                if(userDTO.getRewardPoint() > discountDTO.getRewardPoint()) {
                    //add discount to table user_discount
                    userDiscountService.addUserDiscount(userDTO, discountDTO);

                    //change user's rewardPoints
                    userDTO.setRewardPoint(userDTO.getRewardPoint() - discountDTO.getRewardPoint());
                    userService.changeRewardPointUser(tokenProvider.getUserFromToken(jwtToken), userDTO);

                    return true;
                }else {
                    return false;
                }
            }
        }
        throw new RuntimeException("Invalid token");
    }

    @GetMapping("/user/discount/{id}")
    public DiscountDTO getDiscount(@RequestHeader("Authorization") String token,
                                   @PathVariable Integer id){
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO = discountService.getDiscountByID(id);
        return discountDTO;
    }

    @GetMapping("/user/reward-point")
    public ResponseEntity<String> getChangeRewardPointUser(@RequestHeader("Authorization") String token){
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);

            if (tokenProvider.validateToken(jwtToken)) {
                String newToken = tokenProvider.generateToken(userService.
                        getUserByNameLogin(tokenProvider.getUserFromToken(jwtToken).getNameLogin()));
                return ResponseEntity.ok(newToken);
            }
        }
        throw new RuntimeException("Invalid token");
    }

    @PostMapping("/discount/add")
    public ResponseEntity<String> addDiscount(@RequestBody DiscountDTO discountDTO){
        discountService.addDiscount(discountDTO);
        return ResponseEntity.ok("Add discount successful");
    }
}

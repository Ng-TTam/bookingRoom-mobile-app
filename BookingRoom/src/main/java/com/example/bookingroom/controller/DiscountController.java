package com.example.bookingroom.controller;

import com.example.bookingroom.dto.reqResp.DiscountDTO;
import com.example.bookingroom.dto.request.UserRequest;
import com.example.bookingroom.service.DiscountService;
import com.example.bookingroom.service.UserDiscountService;
import com.example.bookingroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @Autowired
    UserDiscountService userDiscountService;

    @Autowired
    UserService userService;

    @GetMapping("/user/discount-unowned")
    public List<DiscountDTO> getListDiscountUnowner(){
        throw new RuntimeException("Invalid token");
    }

    @GetMapping("/user/discount-owned")
    public List<DiscountDTO> getListDiscountOwner(){
        throw new RuntimeException("Invalid token");
    }

    @PostMapping("/user/exchange-discount")
    public Boolean exchangeDiscount(@RequestBody DiscountDTO discountDTO){
        throw new RuntimeException("Invalid token");
    }

    @GetMapping("/user/discount/{id}")
    public DiscountDTO getDiscount(@PathVariable Integer id){

        return null;
    }

    @GetMapping("/user/reward-point")
    public ResponseEntity<String> getChangeRewardPointUser(){
        throw new RuntimeException("Invalid token");
    }

    @PostMapping("/discount/add")
    public ResponseEntity<String> addDiscount(@RequestBody DiscountDTO discountDTO){
        discountService.addDiscount(discountDTO);
        return ResponseEntity.ok("Add discount successful");
    }
}

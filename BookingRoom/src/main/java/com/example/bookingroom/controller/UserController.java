package com.example.bookingRoom.controller;

import com.example.bookingRoom.dto.request.UserChangeInfoRequest;
import com.example.bookingRoom.dto.request.UserChangePassRequest;
import com.example.bookingRoom.dto.request.UserCreationRequest;
import com.example.bookingRoom.dto.response.UserResponse;
import com.example.bookingRoom.dto.ApiResponse;
import com.example.bookingRoom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> register(@RequestBody UserCreationRequest userRequest){
        return ApiResponse.<UserResponse>builder()
                .result(userService.registerUser(userRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<UserResponse> getUserInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getInfo())
                .build();
    }

    @PostMapping("/change-password")
    public ApiResponse<UserResponse> changePassword(@RequestBody UserChangePassRequest userChangePassRequest){
        return ApiResponse.<UserResponse>builder()
                .result(userService.changePassUser(userChangePassRequest))
                .build();
    }

    @PostMapping("/change-info")
    public ApiResponse<UserResponse> changeInfo(@RequestBody UserChangeInfoRequest userChangeInfoRequest){
        return ApiResponse.<UserResponse>builder()
                .result(userService.changeInfoUser(userChangeInfoRequest))
                .build();
    }
}
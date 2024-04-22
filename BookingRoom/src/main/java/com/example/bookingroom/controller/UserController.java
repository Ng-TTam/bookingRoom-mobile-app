package com.example.bookingroom.controller;

import com.example.bookingroom.dto.UserDTO;
import com.example.bookingroom.jwt.JwtTokenProvider;
import com.example.bookingroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        if(userService.getUserByNameLogin(userDTO.getNameLogin()) == null) {
            userService.registerUser(userDTO);
            return ResponseEntity.ok("User registered successfully!");
        } else{
            return ResponseEntity.ok("User have already exist!");
        }
    }


    @PostMapping("/login")
    public String authenticateUser(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");

        UserDTO userDTO = userService.getUserByNameLogin(username);

        if(userService.equalPassword(userDTO, password)){
            String token = tokenProvider.generateToken(userDTO);
            return token;
        } else {
            return null;
        }
    }

    @GetMapping("/user")
    public UserDTO getUserInfo(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            if (tokenProvider.validateToken(jwtToken)) {
                UserDTO userDTO = tokenProvider.getUserFromToken(jwtToken);
                return userDTO;
            }
        }
        throw new RuntimeException("Invalid token");
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String token,
                                                 @RequestBody Map<String, String> requestBody ){
        String currentPassword = requestBody.get("currentPassword");
        String newPassword = requestBody.get("newPassword");
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);

            if (tokenProvider.validateToken(jwtToken)) {
                UserDTO userDTO = userService.getUserByNameLogin(
                        tokenProvider.getUserFromToken(jwtToken).getNameLogin());

                if(userService.equalPassword(userDTO, currentPassword)){
                    userService.changePassUser(userDTO,newPassword);
                    return ResponseEntity.ok("Change password successful");
                } else{
                    return ResponseEntity.ok("Wrong current password");
                }
            }
        }
        throw new RuntimeException("Invalid token");
    }

    @PostMapping("/change-info")
    public ResponseEntity<String> changeInfo(@RequestHeader("Authorization") String token,
                                                 @RequestBody UserDTO userDTO ){
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);

            if (tokenProvider.validateToken(jwtToken)) {
                userService.changeInfoUser(tokenProvider.getUserFromToken(jwtToken), userDTO);
                String newToken = tokenProvider.generateToken(userService.
                        getUserByNameLogin(tokenProvider.getUserFromToken(jwtToken).getNameLogin()));
                return ResponseEntity.ok(newToken);
            } else{
                return ResponseEntity.ok("Somethings wrong with info");
            }
        }
        throw new RuntimeException("Invalid token");
    }
}
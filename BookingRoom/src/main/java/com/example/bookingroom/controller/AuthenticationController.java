package com.example.bookingRoom.controller;

import com.example.bookingRoom.dto.request.AuthenticationRequest;
import com.example.bookingRoom.dto.request.LogoutRequest;
import com.example.bookingRoom.dto.request.RefreshRequest;
import com.example.bookingRoom.dto.request.VerifyTokenRequest;
import com.example.bookingRoom.dto.response.AuthenticationResponse;
import com.example.bookingRoom.dto.response.TokenResponse;
import com.example.bookingRoom.dto.response.VerifyTokenResponse;
import com.example.bookingRoom.service.Imp.AuthenticationService;
import com.example.bookingRoom.dto.ApiResponse;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticate(authenticationRequest))
                .build();
    }

    @PostMapping("/verify")
    ApiResponse<VerifyTokenResponse> verify(@RequestBody VerifyTokenRequest verifyTokenRequest)
            throws JOSEException, ParseException{
        return ApiResponse.<VerifyTokenResponse>builder()
                .result(authenticationService.verify(verifyTokenRequest))
                .build();
    }

    @PostMapping("/refresh-token")
    ApiResponse<TokenResponse> refreshToken(@RequestBody RefreshRequest refreshRequest)
            throws JOSEException, ParseException{
        return ApiResponse.<TokenResponse>builder()
                .result(authenticationService.refreshToken(refreshRequest))
                .build();
    }

    @PostMapping("/log-out")
    ResponseEntity<String> logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException{
        authenticationService.logout(logoutRequest);
        return ResponseEntity.ok("Log out successful");
    }

}

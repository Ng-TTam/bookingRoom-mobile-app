package com.example.bookingRoom.service;

import com.example.bookingRoom.dto.request.UserChangeInfoRequest;
import com.example.bookingRoom.dto.request.UserChangePassRequest;
import com.example.bookingRoom.dto.request.UserCreationRequest;
import com.example.bookingRoom.dto.request.UserRequest;
import com.example.bookingRoom.dto.response.UserResponse;

public interface UserService {
    UserResponse registerUser(UserCreationRequest userCreationRequest);
    void changeRewardPointUser(UserRequest userDTOCurrent, UserRequest userDTONew);
    UserResponse getInfo();
    UserResponse changePassUser(UserChangePassRequest userChangePassRequest);
    UserResponse changeInfoUser(UserChangeInfoRequest userChangeInfoRequest);
}

package com.example.bookingroom.service;

import com.example.bookingroom.dto.request.UserChangeInfoRequest;
import com.example.bookingroom.dto.request.UserChangePassRequest;
import com.example.bookingroom.dto.request.UserCreationRequest;
import com.example.bookingroom.dto.request.UserRequest;
import com.example.bookingroom.dto.response.UserResponse;
import org.hibernate.sql.Delete;

import java.util.List;

public interface UserService {
    UserResponse registerUser(UserCreationRequest userCreationRequest);
    void changeRewardPointUser(UserRequest userDTOCurrent, UserRequest userDTONew);
    UserResponse getInfo();
    List<UserResponse> getListUser();
    UserResponse changePassUser(UserChangePassRequest userChangePassRequest);
    UserResponse changeInfoUser(UserChangeInfoRequest userChangeInfoRequest);
    void deleteUser(int id);
    void deleteUser();
}

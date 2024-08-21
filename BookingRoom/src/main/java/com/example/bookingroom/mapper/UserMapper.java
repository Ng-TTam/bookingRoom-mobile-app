package com.example.bookingRoom.mapper;

import com.example.bookingRoom.dto.request.UserChangeInfoRequest;
import com.example.bookingRoom.dto.request.UserCreationRequest;
import com.example.bookingRoom.dto.request.UserRequest;
import com.example.bookingRoom.dto.response.UserResponse;
import com.example.bookingRoom.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);

    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserRequest userRequest);

    void updateInfoUser(@MappingTarget User user, UserChangeInfoRequest userChangeInfoRequest);
}

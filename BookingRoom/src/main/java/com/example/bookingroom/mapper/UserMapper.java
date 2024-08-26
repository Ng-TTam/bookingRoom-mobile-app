package com.example.bookingroom.mapper;

import com.example.bookingroom.dto.request.UserChangeInfoRequest;
import com.example.bookingroom.dto.request.UserCreationRequest;
import com.example.bookingroom.dto.request.UserRequest;
import com.example.bookingroom.dto.response.UserResponse;
import com.example.bookingroom.entity.User;
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

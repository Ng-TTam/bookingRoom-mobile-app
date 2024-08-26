package com.example.bookingroom.mapper;

import com.example.bookingroom.dto.request.PermissionRequest;
import com.example.bookingroom.dto.response.PermissionResponse;
import com.example.bookingroom.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);
}

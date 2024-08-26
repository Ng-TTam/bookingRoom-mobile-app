package com.example.bookingroom.mapper;

import com.example.bookingroom.dto.request.RoleRequest;
import com.example.bookingroom.dto.response.RoleResponse;
import com.example.bookingroom.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}

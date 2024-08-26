package com.example.bookingroom.service;

import com.example.bookingroom.dto.request.RoleRequest;
import com.example.bookingroom.dto.response.RoleResponse;
import com.example.bookingroom.entity.Role;

import java.util.List;

public interface RoleService {
    RoleResponse create(RoleRequest roleRequest);
    List<RoleResponse> getAllRoles();
    void delete(String role);

}

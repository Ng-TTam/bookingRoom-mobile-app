package com.example.bookingroom.service;

import com.example.bookingroom.dto.request.PermissionRequest;
import com.example.bookingroom.dto.response.PermissionResponse;
import com.example.bookingroom.entity.Permission;

import java.util.List;

public interface PermissionService {
    PermissionResponse create(PermissionRequest permissionRequest);
    List<PermissionResponse> getAll();
    void deletePermission(String permission);
}

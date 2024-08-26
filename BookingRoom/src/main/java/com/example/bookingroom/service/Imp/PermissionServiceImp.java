package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.request.PermissionRequest;
import com.example.bookingroom.dto.response.PermissionResponse;
import com.example.bookingroom.entity.Permission;
import com.example.bookingroom.mapper.PermissionMapper;
import com.example.bookingroom.repository.PermissionRepository;
import com.example.bookingroom.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImp implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PermissionResponse create(PermissionRequest permissionRequest) {
        Permission permission = permissionRepository.save(permissionMapper.toPermission(permissionRequest));
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePermission(String permission) {
        permissionRepository.deleteById(permission);
    }
}
